package com.example.habitbread.repository

import android.util.Log
import com.example.habitbread.`interface`.DetailHandler
import com.example.habitbread.`interface`.HabitListHandler
import com.example.habitbread.api.ServerImpl
import com.example.habitbread.data.DetailResponse
import com.example.habitbread.data.HabitResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback as Callback

class DetailRepository {

    val TAG :String? = "HabitBread"
    private val habitBreadAPI = ServerImpl.APIService
    lateinit var detailData : DetailResponse

    fun getDetailData(handler : DetailHandler, habitId: Int, year: Int, month: Int){
        val call: Call<DetailResponse> = habitBreadAPI.getHabitDetail(habitId, year, month)
        call.enqueue(
            object : Callback<DetailResponse>{
                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    Log.e(TAG, "Server connect fail")
                }
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    detailData = response.body()!!
                    handler.onResult(detailData);
                    Log.d("chohee서버성공", detailData.toString())
                }
            }
        )
    }
}