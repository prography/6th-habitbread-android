package com.habitbread.main.base

import android.app.Application
import com.habitbread.main.util.SharedPreferences

class BaseApplication : Application() {

    companion object {
        lateinit var preferences : SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        preferences = SharedPreferences(applicationContext)
    }
}
