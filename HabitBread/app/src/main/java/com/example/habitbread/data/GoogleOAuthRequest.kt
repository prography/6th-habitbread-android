package com.example.habitbread.data

import com.google.gson.annotations.SerializedName

data class GoogleOAuthRequest(
    @SerializedName("idToken")
    val idToken: String
)