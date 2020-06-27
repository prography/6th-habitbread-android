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
    var allRanks : RankResponse? = null
    var allHabitListData : MutableLiveData<List<HabitResponse>> = MutableLiveData()

    fun getHabitList(): MutableLiveData<List<HabitResponse>> {
        runBlocking {
            val req = habitBreadAPI.getAllHabitLists()
            val res = req.await()
            Log.d("HabitBread", res.toString())
            allHabitListData.postValue(res)
        }
        return allHabitListData
    }

    fun postNewHabit(body : NewHabitReq): MutableLiveData<List<HabitResponse>>{
        runBlocking {
            val postReq = habitBreadAPI.postNewHabit(body)
            val postRes = postReq.await()
            val getReq = habitBreadAPI.getAllHabitLists()
            val getRes = getReq.await()
            allHabitListData.postValue(getRes)
        }
        return allHabitListData
    }

    fun getAllRanks(handler: RankHandler) {
        val call : Call<RankResponse> = habitBreadAPI.getAllRankings()
        call.enqueue(
            object: Callback<RankResponse>{
                override fun onFailure(call: Call<RankResponse>, t: Throwable) {
                    Log.e(TAG, "Server connect failed")
                }
                override fun onResponse(
                    call: Call<RankResponse>,
                    response: Response<RankResponse>
                ) {
                    Log.d(TAG, response.toString());
                    allRanks = response.body()
                    handler.onResult(allRanks!!.user, allRanks!!.userTotalCount, allRanks!!.rankings)
                }
            }
        )
    }
}