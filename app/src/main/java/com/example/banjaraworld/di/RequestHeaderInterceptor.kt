package com.example.banjaraworld.di

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class RequestHeaderInterceptor:Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
        val request = requestBuilder.build()
        Log.d("TAG", "intercept: $request")
        return chain.proceed(request)
    }
}