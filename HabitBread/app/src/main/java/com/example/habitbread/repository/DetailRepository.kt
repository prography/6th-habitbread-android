package com.example.habitbread.repository

import android.util.Log
import com.example.habitbread.api.ServerImpl
import com.example.habitbread.data.CommitResponse
import com.example.habitbread.data.DetailResponse
import kotlinx.coroutines.runBlocking
import retrofit2.await
import retrofit2.awaitResponse

class DetailRepository {

    val TAG :String? = "HabitBread"
    private val habitBreadAPI = ServerImpl.APIService
    private lateinit var detailData : DetailResponse
    private lateinit var commitResponse: CommitResponse

    fun getDetailData(habitId: Int, year: Int, month: Int): DetailResponse {
        runBlocking {
            val request = habitBreadAPI.getHabitDetail(habitId, year, month)
            val response = request.await()
            detailData = response
        }
        return detailData
    }

    fun getCommit(habitId: Int) : Any {
        var isCommit: Boolean = false
        var isLevelUp: Boolean = false
        runBlocking {
            val request = habitBreadAPI.postCommit(habitId)
            val response = request.awaitResponse()
            if(response.code() == 800) {
                isCommit = true
                return@runBlocking
            }else if(response.code() == 201 && response.body() == null){
                isLevelUp = false
                return@runBlocking
            }else {
                // 레벨업 했을
                Log.d("chohee", response.body().toString())
                //commitResponse = response.body()
                return@runBlocking
            }
        }

        if(isCommit == true) {
            return "습관빵을 이미 구웠습니다!"
        }else if(isLevelUp == true) {
            return commitResponse
        }else {
            Log.d("chohee", "커밋 성")
            return Unit
        }
   }
}