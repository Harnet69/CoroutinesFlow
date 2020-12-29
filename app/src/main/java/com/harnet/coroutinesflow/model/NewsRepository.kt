package com.harnet.coroutinesflow.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepository {
    companion object{
        private const val BASE_URL = "https://raw.githubusercontent.com"
        private const val NEWS_DELAY = 3000L
    }

    private val newsService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsService::class.java)

    fun getNewsArticles(): Flow<Article>{
        // convert a data from API to a flow
        return flow {
            //get data from a backend
            var newSource = newsService.getNews()
            newSource.forEach {
                emit(it)
                kotlinx.coroutines.delay(NEWS_DELAY)
            }
        }
    }
}