package com.android.fundamentals.workshop02

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fundamentals.domain.login.LoginInteractor
import com.android.fundamentals.domain.login.LoginResult
import kotlinx.coroutines.launch

class Workshop2ViewModel(
    private val interactor: LoginInteractor
) : ViewModel() {
    private var _state = MutableLiveData<State>(State.Default())
    val state: LiveData<State> get() = _state

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            _state.value = State.Loading()
            val loginResult = interactor.login(userName = userName, password = password)

            val loginHandle = when(loginResult) {
                is LoginResult.Success -> State.Success()
                is LoginResult.Error.Password -> State.PasswordError()
                is LoginResult.Error.UserName -> State.UserNameError()
            }
            _state.value = loginHandle
        }
    }

    sealed class State {
        class Default : State()
        class Loading : State()
        class UserNameError : State()
        class PasswordError : State()
        class Success : State()
    }
}