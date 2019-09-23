package com.example.logindemo.ui.auth

import android.os.Bundle
import com.example.logindemo.ui.base.BaseActivity
import com.example.logindemo.ui.base.Navigator
import com.example.logindemo.ui.login.LoginFragment


class AuthActivity : BaseActivity() {

    override fun layoutId(): Int = com.example.logindemo.R.layout.activty_auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        Navigator(supportFragmentManager).replaceFragment(com.example.logindemo.R.id.fl_auth, LoginFragment())
    }
}