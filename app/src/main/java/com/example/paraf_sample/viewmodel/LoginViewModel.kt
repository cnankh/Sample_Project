package com.example.paraf_sample.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paraf_sample.model.User
import com.example.paraf_sample.services.login.LoginApiService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val service = LoginApiService()
    private val disposable = CompositeDisposable()

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext


    val user = MutableLiveData<User>()

    val username = MutableLiveData<String>()
    private val _usernameDisplayed = MutableLiveData<String>()
    val usernameDisplayed: LiveData<String>
        get() = _usernameDisplayed


    val password = MutableLiveData<String>()
    private val _passwordDisplayed = MutableLiveData<String>()
    val passwordDisplayed: LiveData<String>
        get() = _passwordDisplayed

    /**
     * take action when login button is pressed
     */
    fun onLoginPressed() {
        if (validateInputs()) {
            createUser()
        }
    }

    /**
     * check if use has given validated data
     */
    private fun validateInputs(): Boolean {
        if (username.value.isNullOrBlank()) {
            Toast.makeText(context, "fill username", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.value.isNullOrBlank()) {
            Toast.makeText(context, "fill password", Toast.LENGTH_SHORT).show()
            return false
        }

        _usernameDisplayed.value = username.value
        _passwordDisplayed.value = password.value
        return true
    }

    /**
     * send request to the server to create a user
     */
    private fun createUser() {
        disposable.add(
            service.createUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<User>() {
                    override fun onSuccess(t: User) {
                        Log.d("tag onSuccess", t.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.d("error", e.message.toString())
                    }

                })
        )
    }
}