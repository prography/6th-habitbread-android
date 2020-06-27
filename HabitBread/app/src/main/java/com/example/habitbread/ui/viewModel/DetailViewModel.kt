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
    var commitIsSuccess: MutableLiveData<Any> = MutableLiveData()
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
                val commitData: Any = DetailRepository().getCommit(habitId)
                if(commitData == "습관빵을 이미 구웠습니다!") {
                    isCommit = true
                }else {
                    isCommit = false
                }
                Log.d("chohee", commitData.toString())
                commitIsSuccess.postValue(commitData)
            }catch (err: Error) {

            }
        }
    }
}