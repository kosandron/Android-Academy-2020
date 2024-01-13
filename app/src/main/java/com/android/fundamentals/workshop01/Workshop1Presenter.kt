package com.android.fundamentals.workshop01

import com.android.fundamentals.domain.login.LoginInteractor
import com.android.fundamentals.domain.login.LoginResult
import kotlinx.coroutines.*

class Workshop1Presenter(
    private val interactor: LoginInteractor,
    private val mainDispatcher: CoroutineDispatcher
) {

    private val presenterScope: CoroutineScope =
        CoroutineScope(SupervisorJob() + mainDispatcher)

    private var view: Workshop1View? = null

    fun attachView(view: Workshop1View) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun onDestroy() {
        presenterScope.cancel()
    }

    fun login(userName: String, password: String) {
        presenterScope.launch {
            //TODO 06: On "view" instance, set loading to true
            view?.setLoading(true)
            val loginResult = interactor.login(userName = userName, password = password)
             when (loginResult) {
                 is LoginResult.Error.Password -> view?.showPasswordError()
                 is LoginResult.Error.UserName -> view?.showUserNameError()
                 is LoginResult.Success -> view?.showSuccess()
             }

            view?.setLoading(false)
        }
    }
}