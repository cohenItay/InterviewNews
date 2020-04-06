package com.itaycohen.interviewnews.data_layer.articles.models

import com.google.gson.annotations.SerializedName

data class HeadlineModel(

    @SerializedName("source")
    val source: SourceModel,

    @SerializedName("author")
    val author: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("url")
    val urlToSourceArticle: String,

    @SerializedName("urlToImage")
    val urlToImage: String,

    @SerializedName("publishedAt")
    val publishedAt: String,

    @SerializedName("content")
    val content: String
)