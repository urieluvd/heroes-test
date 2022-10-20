package com.ezamora.test.api

import com.ezamora.test.globals.Constants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import java.math.BigInteger
import java.security.MessageDigest

class ApiAuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {

        val ts = System.currentTimeMillis().toString()
        val original = chain.request()

        val httpUrl = original.url.newBuilder()
            .addQueryParameter("ts", ts)
            .addQueryParameter("apikey", Constants.API_KEY )
            .addQueryParameter("hash", getHash(ts))
            .build()

        Timber.i("URL $httpUrl")

        val request: Request = original.newBuilder().url(httpUrl).build()

        proceed(request)

    }

    private fun getHash(ts: String): String {
        return toMD5(ts + Constants.PRIVATE_KEY + Constants.API_KEY)
    }

    private fun toMD5(str: String): String {
        val digest = MessageDigest.getInstance("MD5")
        return BigInteger(1, digest.digest(str.toByteArray())).toString(16).padStart(32, '0')
    }

}