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
    private var commitResponse: CommitResponse? = null

    fun getDetailData(habitId: Int, year: Int, month: Int): DetailResponse {
        runBlocking {
            val request = habitBreadAPI.getHabitDetail(habitId, year, month)
            val response = request.await()
            detailData = response
        }
        return detailData
    }

    fun getCommit(habitId: Int)  {
        runBlocking {
            val request = habitBreadAPI.postCommit(habitId)
            val response = request.awaitResponse()
            Log.d("chohee", response.body().toString())
            Log.d("chohee", response.code().toString())
            // 303일 때 body = null 임
            // 201일 때(레벨업 안했을 때) body = CommitResponse(itemId=null, name=null, description=null, level=null)
            // 201일 때(레벨업 했을 때)

//            if(response.code() == 303) {
//                Log.d("chohee", "이미 커밋")
//                commitResponse = response
//                return@runBlocking
//            }else if(response.code() == 201 && response.body() == null){
//                Log.d("chohee", "커밋 성공")
//                commitResponse = response
//                return@runBlocking
//            }else {
//                Log.d("chohee", "레벨업")
//                commitResponse = response
//                return@runBlocking
//            }
        }
        //return commitResponse
   }
}