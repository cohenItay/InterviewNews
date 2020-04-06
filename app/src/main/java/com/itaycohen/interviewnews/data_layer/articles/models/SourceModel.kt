package com.itaycohen.interviewnews.data_layer.articles.models

import com.google.gson.annotations.SerializedName

data class SourceModel(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String
)