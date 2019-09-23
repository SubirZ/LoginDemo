package com.example.logindemo.ui.login

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.logindemo.R
import com.example.logindemo.ui.base.BaseFragment
import com.example.logindemo.ui.base.Navigator
import com.example.logindemo.ui.forgotPassword.ForgotPasswordFragment
import com.example.logindemo.ui.signup.SignupFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.fragment_login

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun initView() {
        viewModel.error.observe(this, Observer<String> { errorMsg ->
            Snackbar.make(btn_login, errorMsg, Snackbar.LENGTH_SHORT).show()
        })

        viewModel.loginResult.observe(this, Observer {
            Snackbar.make(btn_login, it, Snackbar.LENGTH_SHORT).show()
        })

        btn_login.setOnClickListener {
            viewModel.validateLoginCredentials(
                et_username.editText?.text.toString(),
                et_password.editText?.text.toString()
            )
        }

        btn_signup.setOnClickListener {
            activity?.supportFragmentManager?.let {
                Navigator(it).addFragment(R.id.fl_auth, SignupFragment(), this)
            }
        }
    }
}
