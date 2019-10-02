package com.example.newsmvp

import com.example.newsmvp.data.entities.Source
import com.example.newsmvp.data.entities.newsapi.SourcesResult
import com.example.newsmvp.data.network.NewsApi
import com.example.newsmvp.data.network.NewsApiService
import com.example.newsmvp.external.SchedulerProvider
import com.example.newsmvp.presentation.newssources.NewsSourcesContract
import com.example.newsmvp.presentation.newssources.NewsSourcesPresenter
import com.example.newsmvp.util.TestSchedulerProvider
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test

/**
 * Created by ivanaazuka on 9/26/2019.
 * Android Engineer
 **/

class NewsSourcesPresenterTest {
    private lateinit var mNewsSourcesPresenter: NewsSourcesPresenter
    private lateinit var mTestScheduler: TestScheduler
    private var mView: NewsSourcesContract.View = mock()
    private var mService: NewsApiService = mock()

    @Before
    fun setup() {
        mTestScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(mTestScheduler)
        mNewsSourcesPresenter = NewsSourcesPresenter(testSchedulerProvider)
        mNewsSourcesPresenter.setView(mView)
    }

    @Test
    fun fetchNewsSourcesSuccess() {
        val source = Source(
            "abc-news",
            "ABC News",
            "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
            "https://abcnews.go.com",
            "general",
            "en",
            "us"
        )
        val sources = listOf(source)
        val result = SourcesResult("ok", sources)
        val language = "en"
        val country = "us"

        doReturn(Flowable.just(result))
            .`when`(mService)
            .getNewsSources(NewsApi.API_KEY, language, country)

        mNewsSourcesPresenter.fetchNewsSources(language, country)

        mTestScheduler.triggerActions()
        verify(mView).showProgressBar()
        verify(mView).hideProgressBar()
    }

    /*
    *  Disconnect the internet connection
    * */
    @Test
    fun fetchNewsSourcesFailed() {
        val source = Source(
            "abc-news",
            "ABC News",
            "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
            "https://abcnews.go.com",
            "general",
            "en",
            "us"
        )
        val sources = listOf(source)
        val result = SourcesResult("ok", sources)
        val language = "en"
        val country = "us"

        doReturn(Flowable.just(result))
            .`when`(mService)
            .getNewsSources(NewsApi.API_KEY, language, country)

        mNewsSourcesPresenter.fetchNewsSources(language, country)

        mTestScheduler.triggerActions()
        verify(mView).showProgressBar()
        verify(mView).hideProgressBar()
    }
}