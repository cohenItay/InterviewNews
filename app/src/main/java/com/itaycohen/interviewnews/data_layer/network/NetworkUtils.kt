package com.itaycohen.interviewnews.data_layer.network

import com.itaycohen.interviewnews.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

fun createDefaultOkHttpClient(
    getChainedRequest: ((chain : Interceptor.Chain)->Request)? = null
) : OkHttpClient =
    OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            getChainedRequest?.let {
                addInterceptor { chain ->
                    chain.proceed(it(chain))
                }
            }
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }.build()