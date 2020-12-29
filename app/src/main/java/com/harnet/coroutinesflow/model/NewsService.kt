package com.harnet.coroutinesflow.model

import retrofit2.http.GET

interface NewsService {
    // entire endpoint: https://raw.githubusercontent.com/DevTides/NewsApi/master/news.json
    @GET("DevTides/NewsApi/master/news.json")
    suspend fun getNews(): List<Article>
}