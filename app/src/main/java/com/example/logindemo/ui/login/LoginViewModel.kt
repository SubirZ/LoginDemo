package com.example.logindemo.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.logindemo.network.model.UserResponseModel
import com.example.logindemo.network.repo.NetworkClient
import com.example.logindemo.utils.NetworkUtils
import com.example.logindemo.utils.ValidationUtils.passwordRegex
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val loginResult = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    fun validateLoginCredentials(userName: String, password: String) {
        when {
            userName.trim().isEmpty() -> onValidationFailure("Please enter username")
            password.trim().isEmpty() -> onValidationFailure("Please enter password")
            !password.matches(passwordRegex) -> {
                onValidationFailure("password must contain at least one lower case, one upper case, one special char and one number")
            }
            else -> onValidationSuccess()
        }
    }

    fun onValidationFailure(msg: String) {
        error.postValue(msg)
    }

    fun onValidationSuccess() {
        if (NetworkUtils(getApplication()).isConnectedToInternet()) {
            loginRequest()
        } else {
            error.postValue("No Internet Connection")
        }
    }

    fun loginRequest() {
        val client = NetworkClient.client.getUserDatafromUserId("43681")
        client.enqueue(object : Callback<UserResponseModel> {
            override fun onFailure(call: Call<UserResponseModel>, t: Throwable) {
                Log.d("Retrofilt fail", t.message)
            }

            override fun onResponse(call: Call<UserResponseModel>, response: Response<UserResponseModel>) {
                Log.d("Response", response.body().toString())
                if (response.code() == 200)
                    loginResult.postValue("Successfully LoggedIn")
            }
        })
    }

    companion object {
        const val TAG = "LOGIN_TAG"
    }
}