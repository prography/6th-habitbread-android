package com.example.habitbread.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.habitbread.`interface`.DetailHandler
import com.example.habitbread.`interface`.UpdateFinishHandler
import com.example.habitbread.base.BaseViewModel
import com.example.habitbread.data.DetailResponse
import com.example.habitbread.data.HabitResponse
import com.example.habitbread.repository.DetailRepository
import com.example.habitbread.repository.HabitRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Error

class DetailViewModel : BaseViewModel(){

    var detailData: MutableLiveData<DetailResponse> = MutableLiveData()

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
}