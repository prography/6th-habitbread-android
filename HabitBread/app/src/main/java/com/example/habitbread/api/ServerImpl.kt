package com.example.habitbread.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServerImpl {
    private const val BASE_URL = "https://habitbread.tk"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val APIService : HabitAPI = retrofit.create(HabitAPI::class.java)
}