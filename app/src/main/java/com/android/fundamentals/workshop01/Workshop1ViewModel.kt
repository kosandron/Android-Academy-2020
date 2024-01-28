package com.android.fundamentals.workshop01

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fundamentals.workshop01.solution.Workshop1SolutionViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class Workshop1ViewModel(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _mutableLoginState = MutableLiveData<LoginResult>()
    private val _mutableLogoutState = MutableLiveData<LogoutResult>()

    val loginState: LiveData<LoginResult> get() = _mutableLoginState
    val logoutState: LiveData<LogoutResult> get() = _mutableLogoutState

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _mutableLoginState.value = LoginResult.Loading()

                withContext(Dispatchers.IO) {
                    delay(DELAY_MILLIS)
                }

                _mutableLoginState.value = when {
                    userName.isEmpty() -> LoginResult.Error.UserName()
                    password.isEmpty() -> LoginResult.Error.Password()
                    else -> {
                        val newToken = UUID.randomUUID().toString()
                        val editor = sharedPreferences.edit()
                        editor.putString("SECRET_KEY", newToken)
                        editor.apply()
                        LoginResult.Success()
                    }
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {

                _mutableLogoutState.value = LogoutResult.Loading()

                withContext(Dispatchers.IO) {
                    delay(1000)
                }

                val editor = sharedPreferences.edit()
                editor.remove("SECRET_KEY")
                editor.apply()
                _mutableLogoutState.value = LogoutResult.Success()
            }
        }
    }

    fun checkUserIsLoggedIn(): Boolean {
        val someWord =  sharedPreferences.getString("SECRET_KEY", null)
        return someWord != null
    }

    companion object {
        const val DELAY_MILLIS: Long = 1_000
    }
}

sealed class LoginResult {

    class Loading : LoginResult()

    class Success : LoginResult()

    sealed class Error : LoginResult() {

        class UserName : Error()

        class Password : Error()
    }
}

sealed class LogoutResult {

    class Loading : LogoutResult()

    class Success : LogoutResult()

    sealed class Error : LogoutResult()
}