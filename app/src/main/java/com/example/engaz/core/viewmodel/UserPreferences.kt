package com.example.engaz.core.viewmodel

import android.content.Context
import com.example.engaz.core.errors.LocalDataException
import com.example.engaz.features.auth.data.entities.login.UserLogin
import com.google.gson.Gson

object UserPreferences {
    private const val PREFS_NAME = "user_prefs"
    private const val KEY_USER = "user"
    private fun serializeObject(user: UserLogin): String {
        val gson = Gson()
        return gson.toJson(user)
    }
    fun saveUser(context: Context, user: UserLogin) {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            val serializedObject = serializeObject(user)
            editor.putString(KEY_USER, serializedObject)
            editor.apply()
        } catch (e : Exception) {
            throw LocalDataException(e.message.toString())
        }
    }

    fun getUser(context: Context): UserLogin? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val userJson = prefs.getString(KEY_USER, null)
        return if (userJson != null) {
            Gson().fromJson(userJson, UserLogin::class.java) // Using Gson to convert JSON to user object
        } else {
            null
        }
    }

    fun clearUser(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.remove(KEY_USER)
        editor.apply()
    }
}
