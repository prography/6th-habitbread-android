package com.example.habitbread.data

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName

data class AccountResponse(
    @SerializedName("name")
    val accountName: String,
    @SerializedName("itemTotalCount")
    val totalItemCount: Int,
    @SerializedName("exp")
    val userExp: Int,
    @SerializedName("percent")
    val percent: Int
)