package com.example.habitbread.base

import java.lang.Exception

class HabitBreadException constructor() : Exception() {
    var name :String? = null
    var code = 0
    init {
    }

    constructor(name: String) : this(){
        this.name = name
        this.code = 0
    }

    constructor(name: String, code: Int) : this() {
        this.name = name
        this.code = code
    }
}