package com.example.paraf_sample.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paraf_sample.model.Post
import com.example.paraf_sample.repository.PostDatabase
import kotlinx.coroutines.launch

class PostDetailsViewModel(application: Application) : BaseViewModel(Application()) {

    val postLiveData = MutableLiveData<Post>()

    fun fetch(uuid: Int) {
        fetchFromDatabase(uuid)
    }

    private fun fetchFromDatabase(uuid: Int) {
        launch {
            val dao = PostDatabase(getApplication()).postDao()
            val result = dao.getPost(uuid)
            postLiveData.value = result
        }
    }
}