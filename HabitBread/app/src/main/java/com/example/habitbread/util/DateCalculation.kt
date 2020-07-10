package com.example.habitbread.util

import android.util.Log
import com.example.habitbread.data.Habits
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateCalculation {
    fun getTodayOfWeek(): Int {
        val calendar: Calendar = Calendar.getInstance()
        val dayOfWeek: Int = calendar.get(java.util.Calendar.DAY_OF_WEEK)-1 // Sun = 0, Mon = 1, Tue = 2, ... , Sat = 6
        return dayOfWeek
    }

    fun getTodayOfWeekWithDate(inputDate: String): Int {
        val dataFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        val date: Date? = dataFormat.parse(inputDate)
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = date!!
        return calendar.get(Calendar.DAY_OF_WEEK)-1
    }

    fun convertUTCtoSeoulTime(inputTime: String): String {
        val utcZone: ZoneId = ZoneId.of("UTC")
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val dateTime: LocalDateTime = LocalDateTime.parse(inputTime, formatter)
        val utcDateTime: ZonedDateTime = dateTime.atZone(utcZone)
        val zdt: ZonedDateTime = utcDateTime.withZoneSameInstant(ZoneId.of("Asia/Seoul"))
        val date: String = zdt.format(DateTimeFormatter.ISO_LOCAL_DATE)
        return date
    }

    fun habitListSorting(habitList: List<Habits>): List<Habits> {
        var sortedList = habitList
        sortedList = habitList.sortedWith(Comparator<Habits> { o1, o2 ->
            when{
                // 1, 0, -1 우선순위로 정렬
                o1.dayOfWeek.substring(DateCalculation().getTodayOfWeek(), DateCalculation().getTodayOfWeek()+1) < o2.dayOfWeek.substring(DateCalculation().getTodayOfWeek(), DateCalculation().getTodayOfWeek()+1) -> 1
                o1.dayOfWeek.substring(DateCalculation().getTodayOfWeek()+1, DateCalculation().getTodayOfWeek()+2) < o2.dayOfWeek.substring(DateCalculation().getTodayOfWeek()+1, DateCalculation().getTodayOfWeek()+2) -> 0
                else -> -1
            }
        })
        return sortedList
    }
}