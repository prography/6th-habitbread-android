package com.habitbread.main.util

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.habitbread.main.base.BaseApplication

class GoogleOauthUtils(context: Context) {
    private val gso =  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("191839451290-81q6qni5lt1s5nad9lfrhahabjtrp2pa.apps.googleusercontent.com")
        .requestEmail()
        .build()
    val client = GoogleSignIn.getClient(context, gso);

    fun isAlreadyLoggedIn() : Boolean{
        return (BaseApplication.preferences.googleIdToken != null && GoogleSignIn.getLastSignedInAccount(client.applicationContext) != null)
    }
}