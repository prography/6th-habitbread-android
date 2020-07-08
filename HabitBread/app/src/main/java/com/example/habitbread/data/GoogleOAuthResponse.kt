package com.example.habitbread.data

import com.google.android.gms.auth.api.credentials.IdToken
import com.google.gson.annotations.SerializedName

data class GoogleOAuthResponse(
    @SerializedName("accessToken")
    val idToken: String,
    @SerializedName("isNewUser")
    val isNewUser: Boolean
)