package com.example.logindemo.ui.forgotPassword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.logindemo.utils.NetworkUtils
import com.example.logindemo.utils.ValidationUtils.isInValidEmail

class ForgotPasswordViewModel(application: Application) : AndroidViewModel(application) {

    val result = MutableLiveData<String>()

    val error = MutableLiveData<String>()

    fun validateLoginCredentials(email: String) {
        when {
            email.trim().isEmpty() -> onValidationFailure("please enter email")
            isInValidEmail(email) -> onValidationFailure("InValid email")
            else -> onValidationSuccess()
        }
    }

    fun onValidationFailure(errorMsg: String) {
        error.postValue(errorMsg)
    }

    fun onValidationSuccess() {
        if (NetworkUtils(getApplication()).isConnectedToInternet()) {
            result.postValue("successfully sent otp")
        } else {
            error.postValue("No Internet Connection")
        }
    }

}