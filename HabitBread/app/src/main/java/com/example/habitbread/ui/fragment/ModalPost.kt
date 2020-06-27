package com.example.habitbread.ui.fragment

class ModalPost(title: String, category: String, dayOfWeek: String, alarmTime: String?){
    var title: String = ""
    var category: String = ""
    var dayOfWeek: String = ""
    var alarmTime: String? = null

    init{
        this.title = title
        this.category = category
        this.dayOfWeek = dayOfWeek
        this.alarmTime = alarmTime
    }
}