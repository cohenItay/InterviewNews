package com.itaycohen.interviewnews.data_layer.articles.di

import com.google.gson.Gson
import com.itaycohen.interviewnews.data_layer.articles.ArticlesWebService
import com.itaycohen.interviewnews.data_layer.network.createDefaultOkHttpClient
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ArticlesNetworkModule {

    @Provides
    fun provideArticlesWebService(retrofit: Retrofit) : ArticlesWebService =
        retrofit.create(ArticlesWebService::class.java)

    @Provides
    fun provideArticlesRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(ARTICLES_BASE_URL)
            .client(
                createDefaultOkHttpClient(
                    ::createChainedRequest
                )
            )
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .build()


    private fun createChainedRequest(chain: Interceptor.Chain): Request =
        chain.request().newBuilder()
            .addHeader(HEADER_API_KEY_KEY, HEADER_API_KEY_VALUE)
            .build()

    private fun createGson() = Gson()


    companion object {
        private const val ARTICLES_BASE_URL = "http://newsapi.org"
        private const val HEADER_API_KEY_KEY = "X-Api-Key"
        private const val HEADER_API_KEY_VALUE = "80e80965ca234148bca36bbbffe9884b"
    }
}