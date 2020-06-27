package com.example.habitbread.api

import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServerImpl {

    val interceptor: AccessTokenInterceptor = AccessTokenInterceptor()
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private const val BASE_URL = "https://habitbread.tk"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val APIService : HabitBreadAPI = retrofit.create(HabitBreadAPI::class.java)
}

class AccessTokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = "eyJhbGciOiJIUzM4NCIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsImlhdCI6MTU5MTM3NzQ0MX0.Dbo0pPBh33Yzfl8nT0BHMFPDEhRLte4NTxmssP_dXeoxPje0-WXYEFqXXj-DNtPf"
        val builder = chain.request().newBuilder().addHeader("Authorization", "Bearer " + token).build()
        return chain.proceed(builder)
    }
}

