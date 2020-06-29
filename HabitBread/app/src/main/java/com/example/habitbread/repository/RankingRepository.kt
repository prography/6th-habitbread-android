package com.example.habitbread.repository

import android.util.Log
import com.example.habitbread.api.ServerImpl
import com.example.habitbread.data.RankResponse
import kotlinx.coroutines.runBlocking
import retrofit2.await

class RankingRepository {
    private val habitBreadAPI = ServerImpl.APIService
    private var allRanks : RankResponse? = null;

    fun getAllRanks() : RankResponse{
        runBlocking {
            val request = habitBreadAPI.getAllRankings()
            val response = request.await()
            Log.d("HabitBread", response.toString());
            allRanks = response
        }
        return allRanks!!;
    }
}