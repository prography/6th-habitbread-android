package com.example.habitbread.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.habitbread.base.BaseViewModel
import com.example.habitbread.data.HabitResponse
import com.example.habitbread.data.NewHabitReq
import com.example.habitbread.repository.HabitRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.util.Log
import java.lang.Error

class HabitViewModel : BaseViewModel(){

    var rvData: MutableLiveData<List<HabitResponse>> = MutableLiveData()

    fun getAllList(){
        GlobalScope.launch {
            try {
                val habitList = HabitRepository().getHabitList()
                rvData.postValue(habitList.value)
            }catch (err: Error){

            }
        }
    }

    fun postHabit(body: NewHabitReq){
        GlobalScope.launch {
            try {
                val habitList = HabitRepository().postNewHabit(body)
                Log.d("choheezz", habitList.value.toString())
                rvData.postValue(habitList.value)
            }catch (err: Error){

            }
        }
    }
//    private var habitListData: List<HabitResponse>? = null
//
//    companion object {
//        private val instance = HabitViewModel();
//        fun getInstance() : HabitViewModel = instance
//    }
//
//    fun getHabitListData() : List<HabitResponse>? {
//        init(updateHandler)
//        return habitListData
//    }
//
//    fun postNewHabit(data : NewHabit){
//        HabitRepository().postNewHabit(data)
//    }
//
//    fun init(handler: UpdateFinishHandler) {
//        HabitRepository().getAllHabits(object : HabitListHandler {
//            override fun onResult(handlerList: List<HabitResponse>?) {
//                habitListData = handlerList
//                handler.onUpdated()
//            }
//        })
//    }
//
//    val updateHandler = object : UpdateFinishHandler {
//        override fun onUpdated() {
//            // do nothing
//        }
//    }
}