package com.example.habitbread.di

import com.example.habitbread.api.HabitAPI
import com.example.habitbread.repository.SampleRepository
import com.example.habitbread.ui.HabitViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    val baseUrl = "localhost:3000" // todo : to change default domain url

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

val repositoryModule = module {
    single { SampleRepository(get()) }
}

val viewModelModule = module {
    viewModel { HabitViewModel(get()) }
}

var diModule = listOf(
    apiModule,
    repositoryModule,
    viewModelModule
)