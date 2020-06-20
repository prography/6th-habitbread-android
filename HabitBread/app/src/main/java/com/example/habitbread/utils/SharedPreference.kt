package com.example.habitbread.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {
    val PREFS_FILENAME = "HabitBread"
    val PREF_USER_ID_TOKEN = "IdToken"
    val preferences: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var myIdToken: String?
        get() = preferences.getString(PREF_USER_ID_TOKEN, "");
        set(value) = preferences.edit().putString(PREF_USER_ID_TOKEN, value).apply()
}

