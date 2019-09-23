package com.example.logindemo.ui.signup

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.logindemo.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : BaseFragment() {

    private val viewModel: SignUpViewModel by lazy {
        ViewModelProviders.of(this).get(SignUpViewModel::class.java)
    }

    override fun layoutId(): Int = com.example.logindemo.R.layout.fragment_signup

    override fun initView() {

        viewModel.errorSignup.observe(this, Observer<String> { errorMsg ->
            Snackbar.make(btn_signup, errorMsg, Snackbar.LENGTH_SHORT).show()
        })

        viewModel.signUpResult.observe(this, Observer<String> { result ->
            Snackbar.make(btn_signup, result, Snackbar.LENGTH_SHORT).show()
        })

        btn_signup.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        viewModel.validateSignUpCredentials(
            et_email.editText?.text.toString(),
            et_mobile.editText?.text.toString(),
            et_password.editText?.text.toString(),
            et_confirm_password.editText?.text.toString()
        )
    }
}