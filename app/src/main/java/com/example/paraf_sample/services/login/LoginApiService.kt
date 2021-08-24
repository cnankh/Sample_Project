package com.example.paraf_sample.services.login

import com.example.paraf_sample.model.User
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LoginApiService {
    private val BASE_URL = "https://node-fake-api-server.herokuapp.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(LoginApi::class.java)

    fun createUser() : Single<User> {
        return api.createUser();
    }
}