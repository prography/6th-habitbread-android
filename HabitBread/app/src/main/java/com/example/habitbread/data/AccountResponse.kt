package com.example.habitbread.data

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName

data class AccountResponse(
    @SerializedName("name")
    val accountName: String,
    @SerializedName("level")
    val userLevel: Int,
    @SerializedName("exp")
    val userExp: Int,
    @SerializedName("levelPercent")
    val userPercentage: Int
)