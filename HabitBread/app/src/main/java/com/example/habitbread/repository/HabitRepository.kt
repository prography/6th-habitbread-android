package com.example.habitbread.repository

import android.util.Log
import com.example.habitbread.api.ServerImpl
import com.example.habitbread.data.HabitListResponse
import com.example.habitbread.data.RankResponse
import com.example.habitbread.data.NewHabitReq
import kotlinx.coroutines.runBlocking
import retrofit2.await
import retrofit2.awaitResponse

class HabitRepository {

    val TAG :String? = "HabitBread"
    private val habitBreadAPI = ServerImpl.APIService
    var allRanks : RankResponse? = null
    private lateinit var allHabitListData : HabitListResponse


    fun getHabitList(): HabitListResponse {
        runBlocking {
            val request = habitBreadAPI.getAllHabitLists()
            val response = request.awaitResponse()
            Log.d(TAG, response.body().toString())
            allHabitListData = response.body()!!
       }
        return allHabitListData
    }

    fun postNewHabit(body : NewHabitReq): HabitListResponse{
        runBlocking {
            val postRequest = habitBreadAPI.postNewHabit(body)
            val postResponse = postRequest.await()
            val getRequest = habitBreadAPI.getAllHabitLists()
            val getResponse = getRequest.await()
            allHabitListData = getResponse
            Log.d("chohee", postResponse.toString())
        }
        return allHabitListData
    }
}