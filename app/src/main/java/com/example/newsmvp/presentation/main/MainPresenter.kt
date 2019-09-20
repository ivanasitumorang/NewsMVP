package com.example.newsmvp.presentation.main

import com.example.newsmvp.data.entities.newsapi.SourcesResult
import com.example.newsmvp.data.network.NewsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter : MainContract.UserActionListener{

    lateinit var mView: MainContract.View
    var _response = String()

    override fun fetchNewsSources(language: String, country: String) {
        mView.showProgressBar()
        NewsApi.retrofitService.getNewsSources(NewsApi.API_KEY, language, country)
            .enqueue(object : Callback<SourcesResult>{
                override fun onFailure(call: Call<SourcesResult>, t: Throwable) {
                    _response = "Failure " + t.message
                    mView.hideProgressBar()
                }

                override fun onResponse(
                    call: Call<SourcesResult>,
                    response: Response<SourcesResult>
                ) {
                    _response = "Total sources : ${response.body()?.sources?.size ?: 0}"
                    mView.setNewsSources(response.body()!!.sources)
                    mView.hideProgressBar()
                }
            })

    }

    fun setView(view: MainContract.View){
        mView = view
    }

}