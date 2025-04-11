package com.mohan.accountmanager.repo

import android.util.Log
import com.mohan.accountmanager.cloud.AuthService
import com.mohan.accountmanager.data.LoginRequest
import com.mohan.accountmanager.data.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(private val authService: AuthService) {

    fun login(email: String, password: String, callback: (token: String?, error: String?) -> Unit) {
        val request = LoginRequest(email, password)
        authService.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    callback(response.body()?.token, null)
                    Log.e("Mohan", "Login Successful: ${response.body()?.token}")
                } else {
                    callback(null, "Login failed")
                    Log.e("Mohan", "Login failed")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback(null, "Network error: ${t.message}")
                Log.e("Mohan", "Network error")
            }
        })
    }
}
