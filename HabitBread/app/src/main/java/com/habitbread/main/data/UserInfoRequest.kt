package com.habitbread.main.data

import com.google.gson.annotations.SerializedName

class UserInfoRequest (
    @SerializedName("name")
    val name : String?,
    @SerializedName("fcmToken")
    val fcmToken: String?
)