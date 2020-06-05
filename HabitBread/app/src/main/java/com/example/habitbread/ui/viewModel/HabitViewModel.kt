package com.example.habitbread.ui.viewModel

import com.example.habitbread.`interface`.HabitListHandler
import com.example.habitbread.`interface`.UpdateFinishHandler
import com.example.habitbread.base.BaseViewModel
import com.example.habitbread.data.HabitResponse
import com.example.habitbread.repository.HabitRepository

class HabitViewModel private constructor(): BaseViewModel(){
    private var habitListData: List<HabitResponse>? = null

    companion object {
        private val instance = HabitViewModel();
        fun getInstance() : HabitViewModel = instance
    }

    fun getHabitListData() : List<HabitResponse>? {
        init(updateHandler)
        return habitListData
    }

    fun init(handler: UpdateFinishHandler) {
        HabitRepository().getAllHabits(object : HabitListHandler {
            override fun onResult(handlerList: List<HabitResponse>?) {
                habitListData = handlerList
                handler.onUpdated()
            }
        })
    }

    val updateHandler = object : UpdateFinishHandler {
        override fun onUpdated() {
            // do nothing
        }
    }
}