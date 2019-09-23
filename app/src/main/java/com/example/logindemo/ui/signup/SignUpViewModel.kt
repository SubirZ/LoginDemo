package com.example.logindemo.ui.signup

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.logindemo.network.model.UserRequest
import com.example.logindemo.utils.NetworkUtils
import com.example.logindemo.utils.ValidationUtils.isInValidEmail
import com.example.logindemo.utils.ValidationUtils.passwordRegex
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    val signUpResult = MutableLiveData<String>()
    val errorSignup = MutableLiveData<String>()

    fun validateSignUpCredentials(email: String, mobile: String, password: String, confirmPassword: String) {
        when {
            email.trim().isEmpty() -> onValidationFailure("Please enter email")
            isInValidEmail(email) -> onValidationFailure("Please enter a valid email")
            mobile.trim().isEmpty() -> onValidationFailure("Please enter mobile number")
            mobile.length < 10 -> onValidationFailure("Please enter a valid mobile number")
            password.trim().isEmpty() -> onValidationFailure("Please enter password")
            !password.matches(passwordRegex) -> {
                onValidationFailure("password must contain at least one lower case, one upper case, one special char and one number")
            }
            confirmPassword.trim().isEmpty() -> {
                onValidationFailure("Please enter confirm password")
            }
            !confirmPassword.matches(passwordRegex) -> {
                onValidationFailure("password must contain at least one lower case, one upper case, one special char and one number")
            }
            password != confirmPassword -> {
                onValidationFailure("password must match confirm password")
            }
            else -> {
                val user = UserRequest(email, mobile, password)
                onValidationSuccess(user)
            }
        }
    }

    fun onValidationFailure(msg: String) {
        errorSignup.value = msg
    }

    fun onValidationSuccess(user: UserRequest) {
        if (NetworkUtils(getApplication()).isConnectedToInternet()) {
            signUpNewUser(user)
        } else {
            errorSignup.postValue("No Internet Connection")
        }
    }

    private fun signUpNewUser(user: UserRequest) {
        val signUpCall = Observable.just(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : Observer<UserRequest> {
                override fun onComplete() {
                    signUpResult.postValue("Signup Successful")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: UserRequest) {
                    apiCall(t)
                }

                override fun onError(e: Throwable) {
                    Log.d("Error", e.message)
                    signUpResult.postValue("Signup Failure")
                }
            })
    }

    fun apiCall(user: UserRequest) {

    }
}