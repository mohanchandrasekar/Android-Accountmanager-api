package com.mohan.accountmanager

import android.app.Application
import com.mohan.accountmanager.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize any global resources or libraries here

        // Initialize Koin
        startKoin {
            androidContext(this@MyApplication) // Context for Android
            modules(appModule)  // List of Koin modules
        }
    }
}