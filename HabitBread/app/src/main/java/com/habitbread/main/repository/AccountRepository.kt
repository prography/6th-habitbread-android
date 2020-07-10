package com.habitbread.main.repository

import android.util.Log
import com.habitbread.main.api.ServerImpl
import com.habitbread.main.data.AccountResponse
import com.habitbread.main.data.BaseResponse
import kotlinx.coroutines.runBlocking
import retrofit2.await

class AccountRepository {
    private val habitBreadAPI = ServerImpl.APIService
    private var userInfo : AccountResponse? = null;
    private var baseResponse : BaseResponse = BaseResponse("");

    fun getUserInfo() : AccountResponse {
        runBlocking {
            val request = habitBreadAPI.getUserInfo()
            val response = request.await();
            Log.d("HabitBread", response.toString())
            userInfo = response
        }
        return userInfo!!;
    }

    fun deleteAccount() : BaseResponse{
        runBlocking {
            val request = habitBreadAPI.deleteAccount()
            val response = request.await()
            baseResponse = response
        }
        return baseResponse
    }
}