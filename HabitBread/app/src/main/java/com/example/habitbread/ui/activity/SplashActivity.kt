package com.example.habitbread.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.habitbread.R
import com.example.habitbread.api.ServerImpl
import com.example.habitbread.base.BaseApplication
import com.example.habitbread.data.GoogleOAuthRequest
import com.example.habitbread.data.GoogleOAuthResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    private val TAG = "HabitBread"
    private var client : GoogleSignInClient? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("191839451290-81q6qni5lt1s5nad9lfrhahabjtrp2pa.apps.googleusercontent.com")
                .requestEmail()
                .build()
        client = GoogleSignIn.getClient(this, gso)
        client!!.silentSignIn().addOnCompleteListener {
            if (it.isSuccessful) {
                handleSignInResult(it)
            }
        }
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
            updateUi(null);
        }
    }

    private fun updateUi(idToken: String?) {
        var intent : Intent? = null;
        if (idToken != null) {
            intent = Intent(this@SplashActivity, MainActivity::class.java)
        } else {
            intent = Intent(this@SplashActivity, LoginActivity::class.java)
        }
        BaseApplication.preferences.googleIdToken = idToken;
        startActivity(intent)
        finish()
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
                updateUi(googleOauthResponse?.idToken);
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