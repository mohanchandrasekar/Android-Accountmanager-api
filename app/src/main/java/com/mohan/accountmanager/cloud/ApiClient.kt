package com.mohan.accountmanager.cloud

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *   "email": "eve.holt@reqres.in",
 *   "password": "cityslicka"
 */
object ApiClient {
    private const val BASE_URL = "https://reqres.in/api/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authService: AuthService = retrofit.create(AuthService::class.java)
}