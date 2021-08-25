package com.example.paraf_sample.services.post

import com.example.paraf_sample.model.Post
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PostApiService {
    private val BASE_URL = "https://jsonplaceholder.typicode.com"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PostApi::class.java)

    fun fetchPosts(): Single<List<Post>> {
        return api.fetchPosts()
    }
}