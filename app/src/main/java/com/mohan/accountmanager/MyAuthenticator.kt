package com.mohan.accountmanager

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager

import android.accounts.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

class MyAuthenticator(private val context: Context) : AbstractAccountAuthenticator(context) {

    companion object {
        const val ACCOUNT_TYPE = "com.mohan.auth"
        const val AUTH_TOKEN_TYPE = "full_access"
    }

    override fun addAccount(
        response: AccountAuthenticatorResponse,
        accountType: String,
        authTokenType: String?,
        requiredFeatures: Array<out String>?,
        options: Bundle?
    ): Bundle {

        Log.e("Mohan", "addAccount called")
        val intent = Intent(context, LoginActivity::class.java)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        return Bundle().apply { putParcelable(AccountManager.KEY_INTENT, intent) }
    }

    override fun getAuthToken(
        response: AccountAuthenticatorResponse,
        account: Account,
        authTokenType: String,
        options: Bundle?
    ): Bundle {
        val am = AccountManager.get(context)
        var authToken = am.peekAuthToken(account, authTokenType)

        if (authToken.isNullOrEmpty()) {
            val password = am.getPassword(account)
            if (password != null) {
                authToken = "token_$password"
            }
        }

        return if (!authToken.isNullOrEmpty()) {
            Bundle().apply {
                putString(AccountManager.KEY_ACCOUNT_NAME, account.name)
                putString(AccountManager.KEY_ACCOUNT_TYPE, account.type)
                putString(AccountManager.KEY_AUTHTOKEN, authToken)
            }
        } else {
            val intent = Intent(context, LoginActivity::class.java)
            intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
            Bundle().apply { putParcelable(AccountManager.KEY_INTENT, intent) }
        }
    }

    override fun editProperties(response: AccountAuthenticatorResponse?, accountType: String?) =
        null

    override fun confirmCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        options: Bundle?
    ) = null

    override fun getAuthTokenLabel(authTokenType: String) = AUTH_TOKEN_TYPE
    override fun updateCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ) = null

    override fun hasFeatures(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        features: Array<out String>?
    ) = null
}
