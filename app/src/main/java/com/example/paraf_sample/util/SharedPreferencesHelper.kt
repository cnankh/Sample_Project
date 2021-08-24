package com.example.paraf_sample.util
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.google.gson.Gson

class SharedPreferencesHelper {

    companion object {

        private var prefs: SharedPreferences? = null

        var instance: SharedPreferencesHelper? = null
        private var LOCK = Any()

        operator fun invoke(context: Context): SharedPreferencesHelper =
            instance ?: synchronized(LOCK) {
                instance ?: buildHelper(context).also {
                    instance = it
                }
            }

         fun buildHelper(context: Context): SharedPreferencesHelper {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferencesHelper()
        }
    }

    fun setToken(token: String) {
        Log.d("tag set token now" , token)
        prefs?.edit(commit = true) {
            putString("token", token)
        }
    }

    fun getToken(): String? {
        Log.d("tag set token now" , "what the fuck")
        return prefs?.getString("token", null)
    }
}