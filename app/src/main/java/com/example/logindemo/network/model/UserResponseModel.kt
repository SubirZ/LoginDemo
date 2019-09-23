package com.example.logindemo.network.model

data class UserResponseModel(
    val id: String,
    val employee_name: String,
    val employee_salary: String,
    val employee_age: String,
    val profile_image: String
)

data class UserRequest(
    val email: String,
    val mobile: String,
    val password: String
)