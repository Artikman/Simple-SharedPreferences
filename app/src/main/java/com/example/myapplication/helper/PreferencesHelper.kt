package com.example.myapplication.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferencesHelper(context: Context) {

    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun put(key: String, value: String) {
        sharedPreferences.edit {
            putString(key, value)
            apply()
        }
    }

    fun put(key: String, value: Boolean) {
        sharedPreferences.edit {
            putBoolean(key, value)
            apply()
        }
    }

    fun getString(key: String) = sharedPreferences.getString(key, null)

    fun getBoolean(key: String) = sharedPreferences.getBoolean(key, false)

    fun clear() {
        sharedPreferences.edit {
            clear()
            apply()
        }
    }

    companion object {
        const val PREFS_NAME = "Preferences"
    }
}