package com.zizou.marvel.data.network

import android.content.Context
import com.zizou.marvel.data.exception.NetworkConnectionLostException
import com.zizou.marvel.data.util.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        with (chain) {
            if (!NetworkUtil.isNetworkAvailable(context)) {
                throw NetworkConnectionLostException()
            }

            val newRequest = request().newBuilder().build()
            proceed(newRequest)
        }
}