package com.example.engaz.core.util.validator

import android.util.Patterns

class Validator() {

    fun isValidEmail( string: String) : Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(string).matches()
    }

    fun isValidPhone( string: String) : Boolean {
        return Patterns.PHONE.matcher(string).matches()
    }

    fun isValidPassCode(string: String) : Boolean{
        return  string.length == 14
    }

    fun isValidUsername( string: String): Boolean{
        return true
    }

}