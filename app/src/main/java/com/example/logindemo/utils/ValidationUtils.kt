package com.example.logindemo.utils

import android.text.TextUtils

object ValidationUtils {

    val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&])(?=\\S+\$).{8,}")

    fun isInValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}