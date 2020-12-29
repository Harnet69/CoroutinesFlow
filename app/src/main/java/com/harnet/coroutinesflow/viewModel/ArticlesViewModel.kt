package com.harnet.coroutinesflow.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harnet.coroutinesflow.model.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticlesViewModel : ViewModel() {
    val coroutinesScope = CoroutineScope(Dispatchers.IO)

    val mArticlesList = MutableLiveData<List<Article>>()
    val mIsLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getFromApi()
    }

    private fun getFromApi(){
        mIsLoading.value = true
        // mock some articles for test purposes
        val articlesFromApi = listOf<Article>(Article("Title1"), Article("Title2"), Article("Title3"))
        coroutinesScope.launch {
            Thread.sleep(3000L)
            coroutinesScope.launch (Dispatchers.Main) {
                retrieveFromArticles(articlesFromApi)
            }

        }
        //TODO implement Retrofit functionality to get articles from API

    }

    private fun retrieveFromArticles(articles: List<Article>){
        mArticlesList.value = articles
        mIsLoading.value = false
    }
}