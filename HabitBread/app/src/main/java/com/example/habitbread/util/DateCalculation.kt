package com.example.habitbread.util

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
        //val date: Date = Date.from(zdt.toInstant())
        return date.toString()
    }
}