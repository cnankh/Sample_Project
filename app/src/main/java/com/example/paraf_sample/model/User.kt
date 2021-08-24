package com.example.paraf_sample.model

import com.google.gson.annotations.SerializedName

data class User(
    val username: String,
    val password: String,
    @SerializedName("auth_token")
    val token: String,
)

