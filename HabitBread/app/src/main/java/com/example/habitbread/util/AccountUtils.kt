package com.example.habitbread.util

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task

class AccountUtils(context: Context) {
    var googleSignInClient : GoogleSignInClient? = null
    init {
        val gso = GoogleSignInOptions.Builder()
            .requestIdToken("191839451290-81q6qni5lt1s5nad9lfrhahabjtrp2pa.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    fun signOut() : Task<Void> {
        return googleSignInClient!!.signOut()
    }

    fun revokeAccess() : Task<Void> {
        return googleSignInClient!!.revokeAccess()
    }
}