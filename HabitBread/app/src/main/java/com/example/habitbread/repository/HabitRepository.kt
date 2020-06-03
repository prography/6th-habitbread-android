package com.example.habitbread.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.habitbread.api.HabitAPI
import com.example.habitbread.api.ServerImpl
import com.example.habitbread.data.HabitResponse
import org.koin.core.KoinComponent
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback as Callback


object HabitRepository : KoinComponent {

    val TAG :String? = "HabitBread"
    private val habitAPI = ServerImpl.APIService

    fun getAllHabits(accessToken: String): List<HabitResponse> {
        var data : List<HabitResponse> = listOf()

        val call: Call<List<HabitResponse>> = habitAPI.getAllHabitLists(accessToken)
        call.enqueue(
            object : Callback<List<HabitResponse>>{
                override fun onFailure(call: Call<List<HabitResponse>>, t: Throwable) {
                    Log.e(TAG, "Server connect fail")
                }

                override fun onResponse(
                    call: Call<List<HabitResponse>>,
                    response: Response<List<HabitResponse>>
                ) {
                    data = response.body()!!
                    Log.d("chohee", response.body().toString())
                }
            }
        )
        return data
    }
}