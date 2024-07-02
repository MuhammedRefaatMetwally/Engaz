package com.example.engaz.core.viewmodel

import android.content.Context
import android.util.Log
import com.example.engaz.core.errors.LocalDataException
import com.example.engaz.features.auth.data.entities.login.UserLogin
import com.google.gson.Gson
import org.web3j.crypto.Credentials
import org.web3j.crypto.ECKeyPair
import org.web3j.utils.Numeric
import java.math.BigInteger

object UserPreferences {
    private const val PREFS_NAME = "user_prefs"
    private const val KEY_USER = "user"
    private fun serializeObject(user: UserLogin): String {
        val gson = Gson()
        return gson.toJson(user)
    }
    private fun serializeObjectAddress(address: String): String {
        val gson = Gson()
        return gson.toJson(address)
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
    fun saveUserAddress(context: Context, address: String) {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("address", address)
            editor.apply()
            Log.d("SUCCESS", "saveUserAddress: SUCCESS")
        } catch (e : Exception) {
            Log.d("FAIL", "saveUserAddress: FAIL")
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

    fun getUserPrivateAddress(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val userAddress = prefs.getString("address", null)
        return userAddress

    }

    fun getUserPublicAddress(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val userAddress = prefs.getString("address", null)


        return userAddress
    }

    fun clearUser(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.remove(KEY_USER)
        editor.apply()
    }
    fun clearUserAddress(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.remove("address")
        editor.apply()
    }


    private fun getPublicKeyFromPrivateKey(privateKey: String): String {
        val privateKeyInBigInteger = BigInteger(privateKey.substring(2), 16) // Remove "0x" prefix
        val keyPair = ECKeyPair.create(privateKeyInBigInteger)
        val publicKeyInBigInteger = keyPair.publicKey
        return publicKeyInBigInteger.toString(16)
    }
}
