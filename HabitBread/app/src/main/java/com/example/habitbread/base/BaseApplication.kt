package com.example.habitbread.base

import android.app.Application
import com.example.habitbread.utils.SharedPreference

class BaseApplication : Application() {
    companion object {
        lateinit var preferences : SharedPreference
    }

    override fun onCreate() {
        preferences = SharedPreference(applicationContext)
        super.onCreate()
    }
}