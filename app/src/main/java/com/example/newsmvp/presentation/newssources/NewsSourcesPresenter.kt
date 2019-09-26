package com.example.newsmvp.presentation.newssources

import com.example.newsmvp.data.entities.Article
import com.example.newsmvp.data.entities.Source
import com.example.newsmvp.data.entities.newsapi.ArticlesResult
import com.example.newsmvp.data.entities.newsapi.SourcesResult
import com.example.newsmvp.data.network.NewsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsSourcesPresenter : NewsSourcesContract.Presenter {

    private lateinit var mView: NewsSourcesContract.View
    private lateinit var mCall: Call<SourcesResult>
    private var sourceList = emptyList<Source>()

    override fun fetchNewsSources(language: String, country: String) {
        mView.showProgressBar()
        mCall = NewsApi.retrofitService.getNewsSources(NewsApi.API_KEY, language, country)
        mCall.enqueue(object : Callback<SourcesResult> {
            override fun onFailure(call: Call<SourcesResult>, t: Throwable) {
                mView.hideProgressBar()
            }

            override fun onResponse(
                call: Call<SourcesResult>,
                response: Response<SourcesResult>
            ) {
                if (response.isSuccessful) {
                    sourceList = response.body()?.sources ?: emptyList()
                    mView.setNewsSources(sourceList)
                    mView.hideProgressBar()
                } else {
                    mView.hideProgressBar()
                }
            }
        })
    }

    override fun setView(view: NewsSourcesContract.View) {
        mView = view
    }

    override fun cancelFetchSources() {
        mCall.cancel()
    }
}