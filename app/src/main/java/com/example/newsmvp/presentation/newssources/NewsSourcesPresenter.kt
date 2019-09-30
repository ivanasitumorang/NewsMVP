package com.example.newsmvp.presentation.newssources

import com.example.newsmvp.data.entities.Article
import com.example.newsmvp.data.entities.Source
import com.example.newsmvp.data.entities.newsapi.ArticlesResult
import com.example.newsmvp.data.entities.newsapi.SourcesResult
import com.example.newsmvp.data.network.NewsApi
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsSourcesPresenter : NewsSourcesContract.Presenter {

    private lateinit var mView: NewsSourcesContract.View
    //    private lateinit var mCall: Call<SourcesResult>
    private var sourceList = emptyList<Source>()
    private var mCompositeDisposable = CompositeDisposable()

    override fun fetchNewsSources(language: String, country: String) {
        mView.showProgressBar()
        mCompositeDisposable.add(
            NewsApi.retrofitService
                .getNewsSources(NewsApi.API_KEY, language, country)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { sourceResult ->
                        mView.setNewsSources(sourceResult.sources)
                        mView.hideProgressBar()
                    },
                    {
                        mView.hideProgressBar()
                    })
        )
//        mCall = NewsApi.retrofitService.getNewsSources(NewsApi.API_KEY, language, country)
//        mCall.enqueue(object : Callback<SourcesResult> {
//            override fun onFailure(call: Call<SourcesResult>, t: Throwable) {
//                mView.hideProgressBar()
//            }
//
//            override fun onResponse(
//                call: Call<SourcesResult>,
//                response: Response<SourcesResult>
//            ) {
//                if (response.isSuccessful) {
//                    sourceList = response.body()?.sources ?: emptyList()
//                    mView.setNewsSources(sourceList)
//                    mView.hideProgressBar()
//                } else {
//                    mView.hideProgressBar()
//                }
//            }
//        })
    }

    override fun setView(view: NewsSourcesContract.View) {
        mView = view
    }

    override fun cancelFetchSources() {
        mCompositeDisposable.clear()
//        mCall.cancel()
    }
}