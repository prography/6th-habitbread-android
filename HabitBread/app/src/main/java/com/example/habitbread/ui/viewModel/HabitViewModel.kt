package com.example.habitbread.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.habitbread.base.BaseViewModel
import com.example.habitbread.data.HabitListResponse
import com.example.habitbread.data.NewHabitReq
import com.example.habitbread.repository.HabitRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Error

class HabitViewModel : BaseViewModel(){

    var rvData: MutableLiveData<HabitListResponse> = MutableLiveData()

    fun getAllList(){
        GlobalScope.launch {
            try {
                val habitList = HabitRepository().getHabitList()
                rvData.postValue(habitList)
            }catch (err: Error){

            }
        }
    }

    fun postHabit(body: NewHabitReq){
        GlobalScope.launch {
            try {
                val habitList = HabitRepository().postNewHabit(body)
                rvData.postValue(habitList)
            }catch (err: Error){

            }
        }
    }
}