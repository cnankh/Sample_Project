package com.example.paraf_sample.services.post

import com.example.paraf_sample.model.Post
import io.reactivex.Single
import retrofit2.http.GET

interface PostApi {
    @GET("/posts")
    fun fetchPosts() : Single<List<Post>>
}