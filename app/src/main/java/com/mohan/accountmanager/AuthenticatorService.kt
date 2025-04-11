package com.mohan.accountmanager

import android.app.Service
import android.content.Intent
import android.os.IBinder


class AuthenticatorService : Service() {
    private var authenticator: MyAuthenticator? = null

    override fun onCreate() {
        authenticator = MyAuthenticator(this)
    }

    override fun onBind(intent: Intent): IBinder? {
        return authenticator?.iBinder
    }
}