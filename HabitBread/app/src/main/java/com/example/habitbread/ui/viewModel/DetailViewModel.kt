package com.example.habitbread.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.habitbread.base.BaseViewModel
import com.example.habitbread.data.CommitResponse
import com.example.habitbread.data.DetailResponse
import com.example.habitbread.repository.DetailRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Error

class DetailViewModel : BaseViewModel(){

    var detailData: MutableLiveData<DetailResponse> = MutableLiveData()
    var commitIsSuccess: MutableLiveData<CommitResponse> = MutableLiveData()
    var isCommit: Boolean = false

    fun getDetailData(habitId: Int, year: Int, month: Int){
        GlobalScope.launch {
            try {
                val data = DetailRepository().getDetailData(habitId, year, month)
                detailData.postValue(data)
            }catch (err: Error){
                Log.d("HabitBread", "error")
            }
        }
    }

    fun getCommit(habitId: Int) {
        GlobalScope.launch {
            try {
                val commitData = DetailRepository().getCommit(habitId)
//                if(commitData.code() == 303){
//                    isCommit = true
//                }else if(commitData.code() == 201 && commitData.body() == null){
//
//                }else{
//
//                }
//                Log.d("chohee", commitData.toString())
                //commitIsSuccess.postValue(commitData)
            }catch (err: Error) {

            }
        }
    }
}