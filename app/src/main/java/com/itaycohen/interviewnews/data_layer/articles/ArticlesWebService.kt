package com.itaycohen.interviewnews.data_layer.articles

import com.itaycohen.interviewnews.data_layer.articles.models.TopHeadlinesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticlesWebService {

    companion object {
        private const val PATH_TOP_HEADLINES = "v2/top-headlines"
    }

    @GET(PATH_TOP_HEADLINES)
    suspend fun getHeadlines(@Query(value = "country") country: String) : Response<TopHeadlinesModel>
}