package com.mohan.accountmanager

import android.accounts.Account
import android.accounts.AccountManager
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mohan.accountmanager.viewmodel.AuthViewModel
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var accountManager: AccountManager

    // Inject ViewModel using Koin
    private val authViewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        accountManager = AccountManager.get(this)

        // Pre-fill the fields with dummy email and password
        preFillFields()

        // Set login button listener
        findViewById<Button>(R.id.login_button).setOnClickListener { doLogin() }

        // Observe login status from ViewModel
        authViewModel.loginStatus.observe(this) { status ->
            // Handle UI updates for login status
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()

            // If login is successful, add account to AccountManager
            if (status.startsWith("Login successful")) {
                val token = status.substringAfter("Token ")
                handleAccountManagerLogin(username.text.toString(), password.text.toString(), token)
            } else {
                Log.e("Mohan", "Login unsuccessful")
            }
        }
    }


    private fun preFillFields() {
        // Set default email and password values
        username.setText("eve.holt@reqres.in")
        password.setText("cityslicka")
    }

    private fun doLogin() {
        val email = username.text.toString()
        val pass = password.text.toString()

        // Trigger login in ViewModel
        authViewModel.login(email, pass)
    }

    private fun handleAccountManagerLogin(user: String, pass: String, token: String) {
        // Create a new account
        val account = Account(user, MyAuthenticator.ACCOUNT_TYPE)

        // Add account to AccountManager
        accountManager.addAccountExplicitly(account, pass, null)

        // Set authentication token for the account
        accountManager.setAuthToken(account, MyAuthenticator.AUTH_TOKEN_TYPE, token)

        // Log successful login and token for debugging
        Log.e("Mohan", "Account added: ${account.name}")
        Log.e("Mohan", "Auth token set: $token")

        // Optionally, start the main activity or navigate to another screen
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
