package com.example.habitbread.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

class DetailActivity : AppCompatActivity() {

    private lateinit var materialCalendarView: MaterialCalendarView

    private val detailViewModel: DetailViewModel = DetailViewModel.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        //setCalendarView()
        setDetailInfo()
        onClickBackArrow()
        //Log.d("choheehabitId", habitId.toString())
    }

    private fun setCalendarView(){
        materialCalendarView = calendarView_habit_detail
        // TODO : 네트워크 통신 구현 시 selectedDayList에 서버에서 얻어온 날짜를 넣어야함
        val selectedDayList: List<CalendarDay> = listOf(CalendarDay.from(2020, 6 , 5) ,CalendarDay.from(2020, 6 , 4), CalendarDay.from(2020, 7 , 25))
        materialCalendarView.addDecorators(DecoratorDays(selectedDayList))
    }

    private fun setDetailInfo(){
        val habitId: Int = intent.getIntExtra("habitId", -1)
        val todayDate: String = LocalDate.now().toString()
        val year = todayDate.substring(0, 4).toInt()
        val month = todayDate.substring(5,7).toInt()

        detailViewModel.init(object : UpdateFinishHandler {
            override fun onUpdated() {
                val detailData = detailViewModel.requestDetailData(habitId, year, month)
                textView_detail_title.text = detailData.habit.title
                textView_continue_value.text = detailData.habit.continuousCount.toString()
                textView_total_value.text = detailData.commitFullCount.toString()
                // TODO : 여기 달력에 커밋된 날짜 setting 해야함
                Log.d("chohee", detailData.habit.commitHistory.toString())
                val commitHistoryList = listOf(detailData.habit.commitHistory)
//                var createdAtList = mutableListOf<String>()
//                for(i in 0..(commitHistoryList.size-1)){
//                    createdAtList.add(commitHistoryList.get(i).createdAt)
//                }
            }
        }, habitId, year, month)
        setCalendarView()
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