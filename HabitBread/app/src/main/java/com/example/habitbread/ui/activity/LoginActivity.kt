package com.example.habitbread.ui.activity

import com.example.habitbread.data.GoogleOAuthRequest
import kotlinx.coroutines.runBlocking
import retrofit2.*

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.habitbread.R
import com.example.habitbread.api.ServerImpl
import com.example.habitbread.data.GoogleOAuthResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private val TAG = "HabitBread"
    var client: GoogleSignInClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("191839451290-81q6qni5lt1s5nad9lfrhahabjtrp2pa.apps.googleusercontent.com")
                .requestEmail()
                .build()
        client = GoogleSignIn.getClient(this, gso)
        button_google_sign_in.setOnClickListener(View.OnClickListener { signIn() })
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
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            updateUi();
        } else {
            Log.d(TAG, "onStart: ")
        }
    }

    private fun signIn() {
        val signInIntent = client!!.signInIntent
        startActivityForResult(signInIntent, 9001)
    }

    private fun updateUi() {
        val intent: Intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
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
                Log.d(TAG, googleOauthResponse!!.idToken)
                val intent :Intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
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