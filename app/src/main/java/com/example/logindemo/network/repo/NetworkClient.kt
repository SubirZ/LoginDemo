package com.example.logindemo.network.repo

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {

    var API_BASE_URL = "http://dummy.restapiexample.com/api/v1/"

    var httpClient = OkHttpClient.Builder()
    var client: RetrofitClient

    var builder = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create()
        )

    var retrofit = builder
        .client(httpClient.build()).build()

    init {
        client = retrofit.create(RetrofitClient::class.java)
    }

}