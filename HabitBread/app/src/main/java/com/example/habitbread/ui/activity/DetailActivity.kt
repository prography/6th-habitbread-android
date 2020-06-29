package com.example.habitbread.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.habitbread.R
import com.example.habitbread.ui.viewModel.DetailViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.activity_detail.*
import java.time.LocalDate

class DetailActivity : AppCompatActivity() {

    private lateinit var materialCalendarView: MaterialCalendarView
    private val detailViewModel: DetailViewModel by viewModels()
    private var habitId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        habitId = intent.getIntExtra("habitId", -1)
        setDetailInfo()
        onClickCommit()
        onClickBackArrow()
    }

    private fun setDetailInfo(){
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

    private fun onClickCommit() {
        button_commit.setOnClickListener {
            detailViewModel.getCommit(habitId)
            detailViewModel.commitIsSuccess.observe(this, Observer {
                if(detailViewModel.isCommit == true) {
                    Toast.makeText(this, "습관빵을 이미 구웠습니다!", Toast.LENGTH_SHORT).show()
                }else {
                   Log.d("chohee", "이어서")
                }
//                if(it == "success"){
//                    //setDetailInfo()
//                }
            })
        }
    }
}