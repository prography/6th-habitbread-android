package com.example.habitbread.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.habitbread.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var materialCalendarView: MaterialCalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setCalendarView()
    }

    private fun setCalendarView(){
        materialCalendarView = calendarView_habit_detail

        // TODO : 네트워크 통신 구현 시 selectedDayList에 서버에서 얻어온 날짜를 넣어야함
        val selectedDayList: List<CalendarDay> = listOf(CalendarDay.from(2020, 6 , 5) ,CalendarDay.from(2020, 6 , 4), CalendarDay.from(2020, 7 , 25))
        materialCalendarView.addDecorators(DecoratorDays(selectedDayList))
    }

    inner class DecoratorDays(dayList: List<CalendarDay>) : DayViewDecorator{
        // TODO : drawable 체크는 테스트임! 디자인 정해지면 정사각형으로 바꾸기
        val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.icon_calendar_test )
        val list = dayList

        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return list.contains(day)
        }

        override fun decorate(view: DayViewFacade?) {
            view?.setSelectionDrawable(drawable!!)
        }
    }
}