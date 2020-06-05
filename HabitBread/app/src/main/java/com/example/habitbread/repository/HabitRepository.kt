package com.example.habitbread.repository

import android.util.Log
import com.example.habitbread.`interface`.HabitListHandler
import com.example.habitbread.api.ServerImpl
import com.example.habitbread.data.HabitResponse
import com.example.habitbread.data.NewHabit
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback as Callback

class HabitRepository {

    val TAG :String? = "HabitBread"
    private val habitBreadAPI = ServerImpl.APIService
    var allHabitListData : List<HabitResponse> = listOf()

    fun getAllHabits(handler : HabitListHandler){
        val call: Call<List<HabitResponse>> = habitBreadAPI.getAllHabitLists()
        call.enqueue(
            object : Callback<List<HabitResponse>>{
                override fun onFailure(call: Call<List<HabitResponse>>, t: Throwable) {
                    Log.e(TAG, "Server connect fail")
                }
                override fun onResponse(
                    call: Call<List<HabitResponse>>,
                    response: Response<List<HabitResponse>>
                ) {
                    allHabitListData = response.body()!!
                    handler.onResult(allHabitListData)
                }
            }
        )
    }

    fun postNewHabit(data : NewHabit){
        val call: Call<HabitResponse> = habitBreadAPI.postNewHabit(data)
        call.enqueue(
            object : Callback<HabitResponse>{
                override fun onFailure(call: Call<HabitResponse>, t: Throwable) {
                    Log.e(TAG, "Server connect fail")
                }

                override fun onResponse(
                    call: Call<HabitResponse>,
                    response: Response<HabitResponse>
                ) {
                    Log.d("chohee", "서버 성공")
                }
            }
        )
    }
}