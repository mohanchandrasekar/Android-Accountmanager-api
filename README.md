## 📱 Android AccountManager Sample App (Kotlin)

This is a fully working sample Android app written in Kotlin that demonstrates how to use **`AccountManager`** to:

* Handle user login and credentials
* Authenticate against a backend (or use test credentials)
* Store and retrieve tokens securely
* Manage user accounts on the device

---

### 🔧 Features

✅ Custom `Authenticator` integrated with Android's `AccountManager`
✅ `LoginActivity` with username/password authentication
✅ Token handling via `ServerAuthenticate` (mock or real API)
✅ Secure storage using `AccountManager`
✅ Autofill login fields on app restart
✅ Fragment-based and Activity-only versions supported
✅ Toggle test mode for offline development

---

### 🗂 Project Structure

| File                      | Purpose                                              |
| ------------------------- | ---------------------------------------------------- |
| `MainActivity.kt`         | Loads saved account and fills login form             |
| `LoginActivity.kt`        | Login screen and logic                               |
| `SignInFragment.kt`       | Optional UI fragment with login logic                |
| `Authenticator.kt`        | Custom `AbstractAccountAuthenticator` implementation |
| `AuthenticatorService.kt` | Service wrapper for the authenticator                |
| `ServerAuthenticate.kt`   | Fake/mock or real API call handler                   |
| `authenticator.xml`       | Defines account type metadata                        |

---

### ✅ Test Mode (No Backend)

You can test login with hardcoded credentials:

```kotlin
username = "1"
password = "1"
```

This bypasses the backend and stores a test token: `"test_token_123"`.

---

### 📄 Required Permissions

Add the following to your `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.AUTHENTICATE_ACCOUNTS" />
<uses-permission android:name="android.permission.MANAGE_ACCOUNTS" />
<uses-permission android:name="android.permission.GET_ACCOUNTS" />
<uses-permission android:name="android.permission.USE_CREDENTIALS" />
```

---

### 🧩 AccountAuthenticator Setup

Declare the authenticator service in your manifest:

```xml
<service android:name=".AuthenticatorService"
         android:exported="true">
    <intent-filter>
        <action android:name="android.accounts.AccountAuthenticator" />
    </intent-filter>
    <meta-data
        android:name="android.accounts.AccountAuthenticator"
        android:resource="@xml/authenticator" />
</service>
```

Inside `res/xml/authenticator.xml`:

```xml
<account-authenticator
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:accountType="com.example.account"
    android:icon="@mipmap/ic_launcher"
    android:smallIcon="@mipmap/ic_launcher"
    android:label="@string/app_name" />
```

---

### 🔐 Real API Mode (Optional)

Set `isTestMode = false` and provide your own API in `ServerAuthenticate.kt`:

```kotlin
val authToken = ServerAuthenticate.login(username, password, vin)
```

Expected API response:

```json
{
  "token": "your_jwt_or_api_token_here"
}
```

---

### 📷 Screenshots (Optional)

> *(You can add screenshots of your login screen, token display, and Android account settings here.)*

---

### 📦 How to Run

1. Clone this repo in Android Studio
2. Enable ViewBinding in `build.gradle`
3. Run the app on an emulator or device
4. Try logging in with:

   * Username: `1`
   * Password: `1`

---
