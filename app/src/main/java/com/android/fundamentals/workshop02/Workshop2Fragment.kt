package com.android.fundamentals.workshop02

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.fundamentals.R

class Workshop2Fragment : Fragment(R.layout.fragment_workshop_1_workshop_2) {

    private val viewModel: Workshop2ViewModel by viewModels { Workshop2ViewModelFactory() }

    private var userNameInput: EditText? = null
    private var passwordInput: EditText? = null
    private var loginBtn: View? = null
    private var loader: View? = null
    private var loginSuccess: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setUpListeners()

        viewModel.state.observe(this.viewLifecycleOwner, this::setState)
    }

    override fun onDestroyView() {
        userNameInput = null
        passwordInput = null
        loginBtn = null
        loader = null
        loginSuccess = null

        super.onDestroyView()
    }

    private fun setState(state: Workshop2ViewModel.State) =
        when (state) {
            is Workshop2ViewModel.State.Default -> {
                setLoading(loading = false)
            }
            is Workshop2ViewModel.State.Loading -> {
                setLoading(loading = true)
            }
            is Workshop2ViewModel.State.UserNameError -> {
                setLoading(loading = false)
                showUserNameError()
            }
            is Workshop2ViewModel.State.PasswordError -> {
                setLoading(loading = false)
                showPasswordError()
            }
            is Workshop2ViewModel.State.Success -> {
                setLoading(loading = false)
                showSuccess()
            }
        }

    private fun setLoading(loading: Boolean) {
        loader?.isVisible = loading
        loginBtn?.isEnabled = !loading
    }

    private fun showUserNameError() {
        userNameInput?.error = getString(R.string.ws01_ws02_user_name_error)
    }

    private fun showPasswordError() {
        passwordInput?.error = getString(R.string.ws01_ws02_password_error)
    }

    private fun showSuccess() {
        loginBtn?.visibility = View.INVISIBLE
        loginSuccess?.visibility = View.VISIBLE
    }

    private fun initViews(view: View) {
        userNameInput = view.findViewById(R.id.fragment_workshop_1_workshop_2_user_name_input)
        passwordInput = view.findViewById(R.id.fragment_workshop_1_workshop_2_password_input)
        loginBtn = view.findViewById(R.id.fragment_workshop_1_workshop_2_login_btn)
        loader = view.findViewById(R.id.fragment_workshop_1_workshop_2_loader)
        loginSuccess = view.findViewById(R.id.fragment_workshop_1_workshop_2_login_success)
    }

    private fun setUpListeners() {
        loginBtn?.setOnClickListener {
            tryToLogin()
        }
    }

    private fun tryToLogin() {
        val inputUserName = userNameInput?.text?.toString().orEmpty()
        val inputPassword = passwordInput?.text?.toString().orEmpty()

        viewModel.login(inputUserName, inputPassword)
    }

    companion object {
        fun newInstance(): Workshop2Fragment = Workshop2Fragment()
    }
}