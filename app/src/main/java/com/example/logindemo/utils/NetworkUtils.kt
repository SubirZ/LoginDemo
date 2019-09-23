package com.example.logindemo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkUtils constructor(private val context: Context) {

    fun isConnectedToInternet(): Boolean {
        return getConnectionType() == NetworkType.MOBILE || getConnectionType() == NetworkType.WIFI
    }

    fun getConnectionType(): NetworkType {
        var result = NetworkType.NO_INTERNET
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = NetworkType.WIFI
                    } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = NetworkType.MOBILE
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = NetworkType.WIFI
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = NetworkType.MOBILE
                    }
                }
            }
        }
        return result
    }
}

enum class NetworkType { NO_INTERNET, MOBILE, WIFI }

fun main() {
    val a: Int? = 4

    println("value ${a ?: "null"}")

    a?.takeIf { it > 10 }?.run { println("a is >10") }

    a?.let {
        println("a is not null")
    }?.run {
        println("a is null")
    }
}