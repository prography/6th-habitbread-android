package com.example.habitbread.base

import android.app.Application
import com.example.habitbread.di.diModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(diModule)
        }
    }
}
