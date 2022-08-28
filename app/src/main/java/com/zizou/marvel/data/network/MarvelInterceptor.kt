package com.zizou.marvel.data.network

import com.zizou.marvel.data.util.EncryptUtil
import okhttp3.Interceptor
import okhttp3.Response

class MarvelInterceptor(
    private var publicKey: String,
    private var privateKey: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        with (chain) {
            val timestamp = "${System.currentTimeMillis()}"
            val newHttpUrl = request().url.newBuilder()
                .addQueryParameter("ts", timestamp)
                .addQueryParameter("apikey", publicKey)
                .addQueryParameter("hash", EncryptUtil.getMD5("$timestamp$privateKey$publicKey"))
                .build()
            val newRequest = request().newBuilder()
                .url(newHttpUrl)
                .build()

            proceed(newRequest)
        }
}