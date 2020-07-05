package com.example.habitbread.repository

import android.util.Log
import com.example.habitbread.api.ServerImpl
import com.example.habitbread.data.AccountResponse
import com.example.habitbread.data.RankResponse
import kotlinx.coroutines.runBlocking
import retrofit2.await

class AccountRepository {
    private val habitBreadAPI = ServerImpl.APIService
    private var userInfo : AccountResponse? = null;

    fun getUserInfo() : AccountResponse {
        runBlocking {
            val request = habitBreadAPI.getUserInfo()
            val response = request.execute().body();
            Log.d("HabitBread", response.toString())
            userInfo = response
        }
        return userInfo!!;
    }
}