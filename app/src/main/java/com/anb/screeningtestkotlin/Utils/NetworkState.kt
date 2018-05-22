package com.anb.screeningtestkotlin.Utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Agung Nursatria on 5/21/2018.
 */

object NetworkState {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }
}