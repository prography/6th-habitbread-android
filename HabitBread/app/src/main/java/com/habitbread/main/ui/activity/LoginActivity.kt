package com.habitbread.main.ui.activity

import com.habitbread.main.data.GoogleOAuthRequest
import retrofit2.*

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.habitbread.main.R
import com.habitbread.main.api.ServerImpl
import com.habitbread.main.base.BaseApplication
import com.habitbread.main.data.GoogleOAuthResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.habitbread.main.util.AccountUtils
import com.habitbread.main.util.GoogleOauthUtils
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private val TAG = "HabitBread"
    lateinit var client: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        button_google_sign_in.setOnClickListener(View.OnClickListener { signIn() })
        client = GoogleOauthUtils(this@LoginActivity).client
        if (AccountUtils(this@LoginActivity).isAlreadyLoggedIn()) {
            client.silentSignIn().addOnCompleteListener {
                if (it.isSuccessful) {
                    handleSignInResult(it)
                } else {
                    button_google_sign_in.visibility = View.VISIBLE
                }
            }
        } else {
           button_google_sign_in.visibility = View.VISIBLE
        }
    }

    public override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 9001) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            if (task.isSuccessful) {
                handleSignInResult(task)
            } else {
                Log.d(TAG, task.exception.toString());
                Log.d(TAG, task.result.toString());
            }
        }
    }

    private fun signIn() {
        val signInIntent = client!!.signInIntent
        startActivityForResult(signInIntent, 9001)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                Log.d(TAG, "handleSignInResult: " + account.idToken)
                sendGoogleOauth(account.idToken)
            } else {
                Log.d(TAG, "handleSignInResult: " + " no Account")
            }

            // Signed in successfully, show authenticated UI.
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("HabitBread", "signInResult:failed code=" + e.statusCode)
        }
    }

    private fun sendGoogleOauth(idToken: String?) {
        val tempRequest = GoogleOAuthRequest(idToken!!);
        val call = ServerImpl.APIService.serverLoginWithGoogle(tempRequest);
        call.enqueue(object : Callback<GoogleOAuthResponse?> {
            override fun onResponse(
                call: Call<GoogleOAuthResponse?>,
                response: Response<GoogleOAuthResponse?>
            ) {
                Log.d(TAG, "onResponse: ${response.body()}")
                val googleOauthResponse = response.body();
                if (googleOauthResponse?.idToken != null) {
                    Log.d(TAG, googleOauthResponse.idToken)
                    BaseApplication.preferences.googleIdToken = googleOauthResponse.idToken
                    val intent: Intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Log.d(TAG, googleOauthResponse.toString());
                    Toast.makeText(this@LoginActivity, "Google Oauth Failed", Toast.LENGTH_SHORT)
                        .show();
                }

            }

            override fun onFailure(
                call: Call<GoogleOAuthResponse?>,
                t: Throwable
            ) {
                Log.d("HabitBread", "Error in Server google oauth")
                t.printStackTrace()
            }
        })
    }
}