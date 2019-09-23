package com.example.newsmvp.presentation.articles

import android.util.Log
import com.example.newsmvp.data.entities.Article
import com.example.newsmvp.data.entities.Source
import com.example.newsmvp.data.entities.newsapi.ArticlesResult
import com.example.newsmvp.data.network.NewsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SourceArticlePresenter : SourceArticlesContract.UserActionListener {

    private lateinit var mView: SourceArticlesContract.View
    private var _response = String()
    private var articleList = emptyList<Article>()
    private var filterArticleList = emptyList<Article>()

    override fun fetchArticlesBySource(sourceId: String) {
        mView.showProgressBar()
        NewsApi.retrofitService.getArticlesBySource(NewsApi.API_KEY, sourceId)
            .enqueue(object : Callback<ArticlesResult> {
                override fun onFailure(call: Call<ArticlesResult>, t: Throwable) {
                    _response = "Failure : " + t.message
                    mView.hideProgressBar()
                    articleList = emptyList()
                }

                override fun onResponse(
                    call: Call<ArticlesResult>,
                    response: Response<ArticlesResult>
                ) {
                    Log.i("SOURCE ARTICLES", "Total articles from ${sourceId} : ${response.body()?.totalResults ?: 0}")
                    articleList = response.body()?.articles ?: emptyList()
                    mView.setArticles(articleList)
                    mView.hideProgressBar()
                }
            })
    }

    override fun searchArticlesByTitle(title: String?) {
        Log.i("data", title?:"TIDAK ADA")
        title?.let {
            filterArticleList = articleList.filter { it.title.toLowerCase().contains(title.toLowerCase()) }
        }

        mView.setArticles(filterArticleList)
    }

    fun setView(view: SourceArticlesActivity){
        mView = view
    }

}