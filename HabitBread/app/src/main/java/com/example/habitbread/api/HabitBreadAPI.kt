package com.example.habitbread.api

import com.example.habitbread.data.*
import retrofit2.Call
import retrofit2.http.*

interface HabitBreadAPI {
    @GET("/habits")
    fun getAllHabitLists(): Call<HabitListResponse>
    @GET("/habits/{habitId}/calendar/{year}/{month}")
    fun getHabitDetail(@Path("habitId") habitId: Int, @Path("year") year: Int, @Path("month") month: Int): Call<DetailResponse>
    @GET("/ranking")
    fun getAllRankings() : Call<RankResponse>
    @POST("/habits")
    fun postNewHabit(@Body body: NewHabitReq): Call<NewHabitRes>
    @POST("/habits/{habitId}/commit")
    fun postCommit(@Path("habitId") habitId: Int): Call<CommitResponse>
    @GET("/items")
    fun getBreads(): Call<List<BreadResponse>?>
    @POST("/oauth/google/verify")
    fun serverLoginWithGoogle(@Body body: GoogleOAuthRequest): Call<GoogleOAuthResponse>
    @DELETE("/habits/{habitId}")
    fun deleteHabit(@Path("habitId") habitId: Int): Call<DeleteHabit>
    @PATCH("/users")
    fun patchUserInfo(@Body body: UserInfoRequest): Call<UserInfoResponse>
    @PUT("/habits/{habitId}")
    fun putChangedHabitData(@Path("habitId") habitId: Int, @Body body: NewChangedHabitReq): Call<NewChangedHabitRes>
}