package com.example.habitbread.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.habitbread.R
import com.example.habitbread.api.HabitBreadAPI
import com.example.habitbread.api.ServerImpl
import com.example.habitbread.data.TempRequest
import com.example.habitbread.data.TempResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
            sendGoogleOauth(account.idToken)
        } else {
            Log.d(TAG, "onStart: ")
        }
    }

    private fun signIn() {
        val signInIntent = client!!.signInIntent
        startActivityForResult(signInIntent, 9001)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account =
                completedTask.getResult(ApiException::class.java)
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
        val tempRequest = TempRequest(idToken!!);
        val call = ServerImpl.APIService.googleOauth(tempRequest);
        call.enqueue(object : Callback<TempResponse?> {
            override fun onResponse(
                call: Call<TempResponse?>,
                response: Response<TempResponse?>
            ) {
                Log.d(TAG, "onResponse: ${response.body}")
                val googleOauthResponse = response.body();

            }
            override fun onFailure(
                call: Call<TempResponse?>,
                t: Throwable
            ) {
                t.printStackTrace()
            }
        })
    }
}