package com.example.habitbread.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.habitbread.base.BaseViewModel
import com.example.habitbread.data.DetailResponse
import com.example.habitbread.repository.DetailRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Error

class DetailViewModel : BaseViewModel(){

    var detailData: MutableLiveData<DetailResponse> = MutableLiveData()
    var commitIsSuccess: MutableLiveData<String> = MutableLiveData()

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
                val isSuccess: String = DetailRepository().getCommit(habitId)
                commitIsSuccess.postValue(isSuccess)
            }catch (err: Error) {

            }
        }
    }
}