package com.mohan.accountmanager.cloud

import com.mohan.accountmanager.data.LoginRequest
import com.mohan.accountmanager.data.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

/**
 *  "token": "QpwL5tke4Pnpja7X4"
 */

interface AuthService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}