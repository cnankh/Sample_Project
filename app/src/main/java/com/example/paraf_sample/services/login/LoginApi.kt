package com.example.paraf_sample.services.login

import com.example.paraf_sample.model.User
import io.reactivex.Single
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {
    @Headers(
        "Content-Type: application/json",
        "X-FakeAPI-Action: register"
    )
    @POST("/")
    fun createUser(): Single<User>
}