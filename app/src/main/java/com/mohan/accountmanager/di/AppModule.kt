package com.mohan.accountmanager.di

import com.mohan.accountmanager.cloud.ApiClient
import com.mohan.accountmanager.repo.AuthRepository
import com.mohan.accountmanager.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Provide AuthService
    single { ApiClient.authService }

    // Provide AuthRepository
    single { AuthRepository(get()) }

    // Provide AuthViewModel
    viewModel { AuthViewModel(get()) }
}
