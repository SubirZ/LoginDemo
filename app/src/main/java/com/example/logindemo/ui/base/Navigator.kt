package com.example.logindemo.ui.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class Navigator constructor(private val manager: FragmentManager) {

    fun addFragment(
        containerId: Int,
        fragment: Fragment,
        fragmentToHide: Fragment
    ) {
        manager.beginTransaction()
            .add(containerId, fragment)
            .addToBackStack(fragment::class.java.simpleName)
            .hide(fragmentToHide)
            .commit()
    }

    fun replaceFragment(
        containerId: Int,
        fragment: Fragment
    ) {
        manager.beginTransaction()
            .replace(containerId, fragment)
            .commit()
    }
}