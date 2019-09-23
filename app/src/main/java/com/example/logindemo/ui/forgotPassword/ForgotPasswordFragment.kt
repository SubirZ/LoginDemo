package com.example.logindemo.ui.forgotPassword

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.logindemo.R
import com.example.logindemo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_forgot_password.*

class ForgotPasswordFragment : BaseFragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ForgotPasswordViewModel::class.java)
    }

    override fun layoutId(): Int = R.layout.fragment_forgot_password

    override fun initView() {
        viewModel.error.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.result.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        btn_send.setOnClickListener {
            viewModel.validateLoginCredentials(et_username.editText?.text.toString())
        }
    }
}