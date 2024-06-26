package com.example.engaz.features.auth.infrastructure.database.user_info_shared_pref

import android.content.Context
import com.example.engaz.core.errors.LocalDataException
import com.example.engaz.features.auth.data.entities.login.User
import com.example.engaz.features.auth.data.entities.login.UserLogin
import com.google.gson.Gson

interface UserInfoSharedPref {

    fun getUserInfo(context: Context) : UserLogin?

    fun saveUserInfo(context: Context, user: UserLogin)

    fun deleteUserInfo(context: Context)

}


class UserInfoSharedPrefImpl : UserInfoSharedPref {

    private val USER_INFO_KEY = "user_info_key"
    private val USER_INFO_PREF_NAME = "user_info_pref_name"

    private fun serializeObject(user: UserLogin): String {
        val gson = Gson()
        return gson.toJson(user)
    }


    override fun getUserInfo(context: Context): UserLogin?{
        try {
            val sharedPreferences = context.getSharedPreferences(USER_INFO_PREF_NAME, Context.MODE_PRIVATE)
            val serializedObject = sharedPreferences.getString(USER_INFO_KEY, null)

            if (serializedObject != null) {
                val gson = Gson()
                return gson.fromJson(serializedObject, UserLogin::class.java)
            }

            return null
        } catch (e : Exception) {
            throw LocalDataException(e.message.toString())
        }

    }
    override fun saveUserInfo(context: Context, user: UserLogin) {
        try {
            val sharedPreferences = context.getSharedPreferences(USER_INFO_PREF_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val serializedObject = serializeObject(user)
            editor.putString(USER_INFO_KEY, serializedObject)
            editor.apply()
        } catch (e : Exception) {
            throw LocalDataException(e.message.toString())
        }

    }

    override fun deleteUserInfo(context: Context) {
        try {
            val sharedPreferences = context.getSharedPreferences(USER_INFO_PREF_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove(USER_INFO_KEY)
            editor.apply()
        } catch (e : Exception) {
            throw LocalDataException(e.message.toString())
        }
    }


}