package com.itaycohen.interviewnews.data_layer.articles.models

import com.google.gson.annotations.SerializedName

data class TopHeadlinesModel(

    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("articles")
    val articlesList: List<HeadlineModel>
)