package com.example.paraf_sample.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.paraf_sample.model.Post
import com.example.paraf_sample.model.User
import com.example.paraf_sample.services.post.PostApiService
import com.example.paraf_sample.util.SharedPreferencesHelper
import com.example.paraf_sample.view.fragments.LoginFragmentDirections
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PostViewModel(application: Application) : AndroidViewModel(application) {
    val service = PostApiService()
    val disposable = CompositeDisposable()

    val posts = MutableLiveData<List<Post>>()

    fun fetch() {
        disposable.add(
            service.fetchPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Post>>() {
                    override fun onSuccess(t: List<Post>) {
                        posts.value = t
                        Log.d("tag posts list:", t.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.d("error", e.message.toString())
                    }

                })
        )
    }
}