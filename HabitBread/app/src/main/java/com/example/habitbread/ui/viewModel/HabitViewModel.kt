package com.example.habitbread.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitbread.data.HabitListResponse
import com.example.habitbread.data.NewHabitReq
import com.example.habitbread.repository.HabitRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Error

class HabitViewModel : ViewModel(){

    var rvData: MutableLiveData<HabitListResponse> = MutableLiveData()

    fun getAllList(){
        GlobalScope.launch {
            try {
                val habitList = HabitRepository().getHabitList()
                rvData.postValue(habitList)
            }catch (err: Error){
                Log.e("HabitBread", err.printStackTrace().toString())
            }
        }
    }

    fun postHabit(body: NewHabitReq){
        GlobalScope.launch {
            try {
                val habitList = HabitRepository().postNewHabit(body)
                rvData.postValue(habitList)
            }catch (err: Error){
                Log.e("HabitBread", err.printStackTrace().toString())
            }
        }
    }
}