package com.example.habitbread.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.habitbread.`interface`.RankHandler
import com.example.habitbread.api.ServerImpl
import com.example.habitbread.data.HabitResponse
import com.example.habitbread.data.RankResponse
import com.example.habitbread.data.NewHabitReq
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Response
import retrofit2.await
import retrofit2.Callback as Callback

class HabitRepository {

    val TAG :String? = "HabitBread"
    private val habitBreadAPI = ServerImpl.APIService
    private lateinit var allHabitListData : List<HabitResponse>

    fun getHabitList(): List<HabitResponse> {
        runBlocking {
            val req = habitBreadAPI.getAllHabitLists()
            val res = req.await()
            allHabitListData = res
        }
        return allHabitListData
    }

    fun postNewHabit(body : NewHabitReq): List<HabitResponse>{
        runBlocking {
            val postReq = habitBreadAPI.postNewHabit(body)
            val postRes = postReq.await()
            val getReq = habitBreadAPI.getAllHabitLists()
            val getRes = getReq.await()
            allHabitListData = getRes
        }
        return allHabitListData
    }
}