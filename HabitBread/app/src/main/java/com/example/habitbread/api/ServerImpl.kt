package com.example.habitbread.api

import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServerImpl {

    val interceptor: AccessTokenInterceptor2 = AccessTokenInterceptor2()
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private const val BASE_URL = "https://habitbread.tk"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val APIService : HabitBreadAPI = retrofit.create(HabitBreadAPI::class.java)
}

class AccessTokenInterceptor2: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = "eyJhbGciOiJIUzM4NCIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjUsImlhdCI6MTU5MTE5NTI3MH0.Z2OJGgH1nf1LfQXpFG62rXegn3V66u7sVeKLunC7maGwrWEGtmJQvGPqJUz2EL04"
        val builder = chain.request().newBuilder().addHeader("Authorization", "Bearer " + token).build()
        return chain.proceed(builder)
    }
}

