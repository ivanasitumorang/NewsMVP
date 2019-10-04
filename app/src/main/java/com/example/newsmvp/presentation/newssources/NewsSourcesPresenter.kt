package com.example.newsmvp.presentation.newssources

import com.example.newsmvp.data.entities.Article
import com.example.newsmvp.data.entities.Source
import com.example.newsmvp.data.entities.newsapi.ArticlesResult
import com.example.newsmvp.data.entities.newsapi.SourcesResult
import com.example.newsmvp.data.network.NewsApi
import com.example.newsmvp.data.network.NewsApiService
import com.example.newsmvp.external.SchedulerProvider
import com.example.newsmvp.presentation.common.BasePresenter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class NewsSourcesPresenter @Inject constructor(private val service: NewsApiService, private val schedulerProvider: SchedulerProvider) : NewsSourcesContract.Presenter {


    private lateinit var mView: NewsSourcesContract.View
    private lateinit var disposable : Disposable

    override fun fetchNewsSources(language: String, country: String) {
        mView.showProgressBar()
        disposable = service
                .getNewsSources(NewsApi.API_KEY, language, country)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    { sourceResult ->
                        mView.setNewsSources(sourceResult.sources)
                        mView.hideProgressBar()
                    },
                    {
                        mView.hideProgressBar()
                    })
    }

    override fun setView(view: NewsSourcesContract.View) {
        mView = view
    }

    override fun cancelFetchSources() {
        disposable.dispose()
    }
}