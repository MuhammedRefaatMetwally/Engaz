package com.example.engaz.core.viewmodel

import android.content.Context
import android.util.Log
import com.example.engaz.core.errors.LocalDataException
import com.example.engaz.features.auth.data.entities.login.UserLogin
import com.google.gson.Gson
import org.web3j.crypto.Credentials
import org.web3j.utils.Numeric

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
        return if (userAddress != null) {
            Gson().fromJson(userAddress, String::class.java) // Using Gson to convert JSON to user object
        } else {
            null
        }
    }
    fun getUserPublicAddress(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val userAddress = prefs.getString("address", null)
        val publicAddress =  privateKeyToPublicKey(userAddress ?:"")

        return if (userAddress != null) {
            Gson().fromJson(publicAddress, String::class.java) // Using Gson to convert JSON to user object
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

    private fun privateKeyToPublicKey(privateKey: String): String {
        // Remove the '0x' prefix if it's present
        val cleanedPrivateKey = privateKey.removePrefix("0x")

        // Convert the private key string to a BigInteger
        val privateKeyBigInt = Numeric.toBigInt(cleanedPrivateKey)

        // Generate the credentials using the private key
        val credentials = Credentials.create(privateKeyBigInt.toString(16))

        // Get the public key from the credentials
        val publicKey = credentials.ecKeyPair.publicKey

        // Return the public key as a hexadecimal string
        return Numeric.toHexStringWithPrefix(publicKey)
    }
}
