package com.example.paraf_sample.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import com.example.paraf_sample.model.Post

@Dao
interface PostDao {

    @Insert
    suspend fun insertAll(vararg posts: Post) : List<Long>

    @Query("SELECT * FROM post")
    suspend fun getAllPosts() : List<Post>

    @Query("SELECT * FROM post WHERE uuid= :postId")
    suspend fun getPost(postId: Int): Post

    @Query("DELETE FROM post")
    suspend fun deleteAllPosts()
}