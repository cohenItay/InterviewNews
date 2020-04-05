package com.itaycohen.interviewnews.data_layer.articles

import retrofit2.http.GET
import retrofit2.http.Path

interface ArticlesWebService {

    companion object {
        private const val PATH_TOP_HEADLINES = "v2/top-headlines{country}"
    }

    @GET(PATH_TOP_HEADLINES)
    fun getHeadlines(@Path(value = "country") country: String)
}