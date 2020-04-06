package com.itaycohen.interviewnews.data_layer.articles

import com.itaycohen.interviewnews.data_layer.articles.models.TopHeadlinesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticlesWebService {

    companion object {
        private const val PATH_TOP_HEADLINES = "v2/top-headlines{country}"
    }

    @GET(PATH_TOP_HEADLINES)
    suspend fun getHeadlines(@Path(value = "country") country: String) : Response<TopHeadlinesModel>
}