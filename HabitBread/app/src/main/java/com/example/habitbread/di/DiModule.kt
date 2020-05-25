package com.example.habitbread.di

import com.example.habitbread.api.HabitAPI
import com.example.habitbread.repository.HabitRepository
import com.example.habitbread.ui.viewModel.HabitViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    val baseUrl = "http://49.50.165.80:3000/"

    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HabitAPI::class.java)
    }

    single {
        OkHttpClient.Builder().addInterceptor(get() as HttpLoggingInterceptor).build()
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}

val viewModelModule = module {
    viewModel { HabitViewModel(get()) }
    single { HabitRepository(get()) }
}

var diModule = listOf(
    apiModule,
    viewModelModule
)