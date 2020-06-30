package com.example.habitbread.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class DateCalculation {
    fun getTodayOfWeek(): Int {
        val calendar: Calendar = Calendar.getInstance()
        val dayOfWeek: Int = calendar.get(java.util.Calendar.DAY_OF_WEEK)-1 // Sun = 0, Mon = 1, Tue = 2, ... , Sat = 6
        return dayOfWeek
    }

    fun getTodayOfWeekWithDate(inputDate: String): Int {
        val dataFormat = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = dataFormat.parse(inputDate)
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.DAY_OF_WEEK)-1
    }
}