package com.example.habitbread.repository

import android.util.Log
import com.example.habitbread.api.ServerImpl
import com.example.habitbread.data.CommitResponse
import com.example.habitbread.data.DetailResponse
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import retrofit2.await
import retrofit2.awaitResponse

class DetailRepository {

    val TAG :String? = "HabitBread"
    private val habitBreadAPI = ServerImpl.APIService
    private lateinit var detailData : DetailResponse

    fun getDetailData(habitId: Int, year: Int, month: Int): DetailResponse {
        runBlocking {
            val request = habitBreadAPI.getHabitDetail(habitId, year, month)
            val response = request.await()
            detailData = response
        }
        return detailData
    }

    fun getCommit(habitId: Int) : String {
        var response: Response<CommitResponse>
        runBlocking {
            val request = habitBreadAPI.getCommit(habitId)
            response = request.awaitResponse()
            Log.d(TAG, response.code().toString())
        }
        // todo: code 문제 해결되면 다시 연결하기
        return response.body()!!.message
    }
}