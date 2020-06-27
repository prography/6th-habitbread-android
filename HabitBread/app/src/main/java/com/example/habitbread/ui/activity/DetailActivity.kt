package com.example.habitbread.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.habitbread.R
import com.example.habitbread.`interface`.UpdateFinishHandler
import com.example.habitbread.ui.viewModel.DetailViewModel
import com.example.habitbread.ui.viewModel.HabitViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.activity_detail.*
import java.time.LocalDate
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var materialCalendarView: MaterialCalendarView
    val detailViewModel: DetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setDetailInfo()
        onClickBackArrow()
    }

    private fun setCalendarView(){
        materialCalendarView = calendarView_habit_detail
        // TODO : 네트워크 통신 구현 시 selectedDayList에 서버에서 얻어온 날짜를 넣어야함
        val selectedDayList: List<CalendarDay> = listOf(CalendarDay.from(2020, 6 , 5) ,CalendarDay.from(2020, 6 , 4), CalendarDay.from(2020, 7 , 25))
        materialCalendarView.addDecorators(DecoratorDays(selectedDayList))
    }

    private fun setDetailInfo(){
        val habitId: Int = intent.getIntExtra("habitId", -1)

        //현 시각 년도, 월 구하기
        val todayDate: String = LocalDate.now().toString()
        val year = todayDate.substring(0, 4).toInt()
        val month = todayDate.substring(5, 7).toInt()

        //calendar setting
        materialCalendarView = calendarView_habit_detail

        detailViewModel.getDetailData(habitId, year, month)
        detailViewModel.detailData.observe(this, Observer {
            textView_detail_title.text = it.habit.title
            textView_continue_value.text = it.habit.continuousCount.toString() + "회"
            textView_total_value.text = it.commitFullCount.toString() + "회"
            val committedDayList: MutableList<CalendarDay> = mutableListOf()
            for(i in 0..it.habit.commitHistory.size-1){
                val year = it.habit.commitHistory[i].createdAt.substring(0, 4).toInt()
                val month = it.habit.commitHistory[i].createdAt.substring(5, 7).toInt()
                val day = it.habit.commitHistory[i].createdAt.substring(8, 10).toInt()
                val aDay = CalendarDay.from(year, month, day)
                committedDayList.add(aDay)
                materialCalendarView.addDecorators(DecoratorDays(committedDayList))
            }
        })
    }

    inner class DecoratorDays(dayList: List<CalendarDay>) : DayViewDecorator{
        // TODO : drawable 체크는 테스트임! 디자인 정해지면 정사각형으로 바꾸기
        val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.icon_calendar_check)
        val list = dayList

        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return list.contains(day)
        }

        override fun decorate(view: DayViewFacade?) {
            view?.setSelectionDrawable(drawable!!)
        }
    }

    fun onClickBackArrow(){
        imageView_back.setOnClickListener {
            finish()
        }
    }
}