package com.example.newsmvp.presentation.articles

import android.annotation.SuppressLint
import android.util.Log
import com.example.newsmvp.data.entities.Article
import com.example.newsmvp.data.entities.newsapi.ArticlesResult
import com.example.newsmvp.data.network.NewsApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SourceArticlePresenter : SourceArticlesContract.Presenter {

    private lateinit var mView: SourceArticlesContract.View
    //    private lateinit var mCall: Call<ArticlesResult>
    var articleList = emptyList<Article>()
    private var filterArticleList = emptyList<Article>()
    private var mCompositeDisposable = CompositeDisposable()

    /**
     * func to fetch articles by certain source from endpoint
     * @param sourceId
     * */
    override fun fetchArticlesBySource(sourceId: String) {
        mView.showProgressBar()
        mCompositeDisposable.add(
            NewsApi.retrofitService
                .getArticlesBySource(NewsApi.API_KEY, sourceId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { articleResult ->
                        articleList = articleResult.articles
                        mView.setArticles(articleList)
                        mView.hideProgressBar()
                    },
                    {
                        mView.hideProgressBar()
                    })
        )
    }

    /**
     * func to filter articles by title
     * @param title
     * */
    @SuppressLint("DefaultLocale", "CheckResult")
    override fun searchArticlesByTitle(title: String?) {

        Observable.fromIterable(articleList)
            .filter {
                it.title!!.toLowerCase().contains(title!!.toLowerCase())
            }
            .toList()
            .subscribe { articles ->
                if (articles.isEmpty()){
                    mView.showNoSearchResult(title)
                } else mView.hideNoSearchResult()

                mView.setArticles(articles)
            }
    }

    override fun setView(view: SourceArticlesContract.View) {
        mView = view
    }

    override fun cancelFetchArticles() {
        mCompositeDisposable.clear()
//        mCall.cancel()
    }

}