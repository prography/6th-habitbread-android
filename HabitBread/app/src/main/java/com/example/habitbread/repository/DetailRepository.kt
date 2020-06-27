package com.example.habitbread.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.habitbread.`interface`.DetailHandler
import com.example.habitbread.`interface`.HabitListHandler
import com.example.habitbread.api.ServerImpl
import com.example.habitbread.data.DetailResponse
import com.example.habitbread.data.HabitResponse
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Response
import retrofit2.await
import retrofit2.awaitResponse
import retrofit2.Callback as Callback

class DetailRepository {

    val TAG :String? = "HabitBread"
    private val habitBreadAPI = ServerImpl.APIService
    private lateinit var detailData : DetailResponse

    fun getDetailData(habitId: Int, year: Int, month: Int): DetailResponse {
        runBlocking {
            val request = habitBreadAPI.getHabitDetail(habitId, year, month)
            val response = request.await();
            Log.d("HabitBread", response.toString())
            detailData = response;
        }
        Log.d("choheeDetail", detailData.toString())
        return detailData
    }
}