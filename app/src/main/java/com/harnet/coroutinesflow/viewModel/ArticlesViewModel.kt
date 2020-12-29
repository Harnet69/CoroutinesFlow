package com.harnet.coroutinesflow.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harnet.coroutinesflow.model.Article

class ArticlesViewModel : ViewModel() {
    val mArticlesList = MutableLiveData<List<Article>>()
    val mIsLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getFromApi()
    }

    private fun getFromApi(){
        mIsLoading.value = true
        // mock some articles for test purposes
        val articlesFromApi = listOf<Article>(Article("Title1"), Article("Title2"), Article("Title3"))
        //TODO implement Retrofit functionality to get articles from API
        Thread.sleep(3000L)
        retrieveFromArticles(articlesFromApi)
    }

    private fun retrieveFromArticles(articles: List<Article>){
        mArticlesList.value = articles
        mIsLoading.value = false
    }
}