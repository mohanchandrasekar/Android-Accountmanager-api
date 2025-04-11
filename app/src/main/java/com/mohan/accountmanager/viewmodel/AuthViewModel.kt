package com.mohan.accountmanager.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohan.accountmanager.repo.AuthRepository

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> = _loginStatus

    fun login(email: String, password: String) {
        authRepository.login(email, password) { token, error ->
            if (token != null) {
                Log.e("Mohan", "Auth token set: $token")
                _loginStatus.postValue("Login successful: Token $token")
            } else {
                Log.e("Mohan", "Auth token error $error")
                _loginStatus.postValue(error ?: "Unknown error")
            }
        }
    }
}
