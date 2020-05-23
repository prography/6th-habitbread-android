package com.example.habitbread.repository

import android.util.Log
import com.example.habitbread.api.HabitAPI
import com.example.habitbread.data.Habit
import com.example.habitbread.data.HabitResponse
import org.koin.core.KoinComponent
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback as Callback

class HabitRepository constructor(habitAPI: HabitAPI) : KoinComponent {
    val TAG :String? = "HabitBread"
    private val habitAPI: HabitAPI = habitAPI;
    fun getHabits() {
        val habits = habitAPI.getHabits(object: Callback<HabitResponse> {
            override fun onFailure(call: Call<HabitResponse>, t: Throwable) {
                Log.e(TAG, t.message, t);
            }

            override fun onResponse(call: Call<HabitResponse>, response: Response<HabitResponse>) {
                Log.d(TAG, response.toString());
            }
        });
    }

    fun getSingleHabit() {
        val habit = habitAPI.getHabit(object: Callback<Habit> {
            override fun onFailure(call: Call<Habit>, t: Throwable) {
                Log.e(TAG, t.message, t)
            }

            override fun onResponse(call: Call<Habit>, response: Response<Habit>) {
                Log.d(TAG, response.toString());
            }

        })
    }

    fun editHabit() {
        val habit = habitAPI.editHabit();
    }

    fun deleteHabit() {
        val habit = habitAPI.deleteHabit();
    }

    fun createHabit() {
        val habit = habitAPI.createHabit(object : Callback<Habit> {
            override fun onFailure(call: Call<Habit>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<Habit>, response: Response<Habit>) {
                TODO("Not yet implemented")
            }

        })
    }
}