package com.harnet.coroutinesflow.model

import com.google.gson.annotations.SerializedName

data class Article(
    val author: String? = null,
    val title: String?  = null,
    val description: String?  = null,
    val url: String?  = null,
    @SerializedName("imageUrl")
    val urlToImage: String?  = null,
    val publishedAt: String?  = null
)