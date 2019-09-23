package com.example.logindemo.network.repo

import com.example.logindemo.network.model.UserResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitClient {

    @GET("/employee/{user}")
    fun getUserDatafromUserId(@Path("user") user: String): Call<UserResponseModel>
}