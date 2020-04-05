package com.itaycohen.interviewnews.data_layer

import com.google.gson.Gson
import com.itaycohen.interviewnews.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class NetworkManagerModule {

    @Provides
    fun provideArticlesRetrofit() : Retrofit {
        val gson = Gson()
        Retrofit.Builder()
            .baseUrl(ARTICLES_BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    private fun createOkHttpClient() : OkHttpClient {
        OkHttpClient
            .Builder().apply {
                retryOnConnectionFailure(true)
                addInterceptor {chain ->
                    chain.proceed(getGlobalRequestBuilderInstance(chain))
                }
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }.build()
    }

    companion object {
        private const val ARTICLES_BASE_URL = "http://newsapi.org"
    }
}