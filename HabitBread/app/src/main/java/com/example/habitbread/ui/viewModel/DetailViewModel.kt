package com.example.habitbread.ui.viewModel

import com.example.habitbread.`interface`.CreationHandler
import com.example.habitbread.`interface`.DetailHandler
import com.example.habitbread.`interface`.HabitListHandler
import com.example.habitbread.`interface`.UpdateFinishHandler
import com.example.habitbread.base.BaseViewModel
import com.example.habitbread.data.DetailResponse
import com.example.habitbread.data.HabitResponse
import com.example.habitbread.repository.DetailRepository
import com.example.habitbread.repository.HabitRepository

class DetailViewModel private constructor(): BaseViewModel(){
    private lateinit var detailInfoData: DetailResponse

    companion object {
        private val instance = DetailViewModel();
        fun getInstance() : DetailViewModel = instance
    }

    fun commitHabit(habitId: Int, handler: CreationHandler) {
        DetailRepository().commitHabit(handler = object : CreationHandler{
            override fun onCreated(isSuccessful: Boolean) {
                handler.onCreated(isSuccessful);
            }
        }, habitId = habitId)
    }

    fun requestDetailData(habitId: Int, year: Int, month: Int): DetailResponse{
        init(updateHandler, habitId, year, month)
        return detailInfoData!!
    }

    fun init(handler: UpdateFinishHandler, habitId: Int, year: Int, month: Int) {
        DetailRepository().getDetailData(object : DetailHandler {
            override fun onResult(handlerList: DetailResponse) {
                detailInfoData = handlerList
                handler.onUpdated()
            }
        }, habitId, year, month)
    }

    val updateHandler = object : UpdateFinishHandler {
        override fun onUpdated() {
            // do nothing
        }
    }
}