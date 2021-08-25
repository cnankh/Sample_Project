package com.example.paraf_sample.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.paraf_sample.model.Post
import com.example.paraf_sample.model.User
import com.example.paraf_sample.repository.PostDatabase
import com.example.paraf_sample.services.post.PostApiService
import com.example.paraf_sample.util.SharedPreferencesHelper
import com.example.paraf_sample.view.fragments.LoginFragmentDirections
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : BaseViewModel(application) {
    private val service = PostApiService()
    private val disposable = CompositeDisposable()

    val posts = MutableLiveData<List<Post>>()


    private fun fetchFromDatabase() {
        launch {
            val posts = PostDatabase(getApplication()).postDao().getAllPosts()
            postsRetrieved(posts)
            Toast.makeText(getApplication(), "Database", Toast.LENGTH_LONG).show()
        }

    }

    private fun postsRetrieved(postList: List<Post>) {
        posts.value = postList
    }

    private fun storePostsInDatabase(list: List<Post>) {
        launch {
            val dao = PostDatabase(getApplication()).postDao()
            dao.deleteAllPosts()
            val result = dao.insertAll(*list.toTypedArray())
            //*list.toTypedArray expand the list into individual elements
            var i = 0
            while (i < list.size) {
                list[i].uuid = result[i].toInt()
                ++i
            }

            postsRetrieved(list)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


    fun fetch() {
        disposable.add(
            service.fetchPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Post>>() {
                    override fun onSuccess(t: List<Post>) {
                        posts.value = t
                        storePostsInDatabase(t)
                        Log.d("tag posts list:", t.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.d("error", e.message.toString())
                    }

                })
        )
    }
}