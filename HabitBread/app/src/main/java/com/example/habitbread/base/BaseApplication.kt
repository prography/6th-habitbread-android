package com.example.habitbread.base

import android.app.Application
import com.example.habitbread.di.diModule
import com.example.habitbread.util.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApplication : Application() {

    companion object {
        lateinit var preferences : SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        preferences = SharedPreferences(applicationContext)
    }
}
