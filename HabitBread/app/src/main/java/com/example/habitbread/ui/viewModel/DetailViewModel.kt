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
    var commitData: MutableLiveData<Response<CommitResponse>> = MutableLiveData()

    fun getDetailData(habitId: Int, year: Int, month: Int){
        GlobalScope.launch {
            try {
                val data = DetailRepository().getDetailData(habitId, year, month)
                detailData.postValue(data)
            }catch (err: Error){
                Log.e("HabitBread", err.printStackTrace().toString())
            }
        }
    }

    fun postCommit(habitId: Int) {
        GlobalScope.launch {
            try {
                val data = DetailRepository().postCommit(habitId)
                commitData.postValue(data)
            }catch (err: Error) {
                Log.e("HabitBread", err.printStackTrace().toString())
            }
        }
    }
}