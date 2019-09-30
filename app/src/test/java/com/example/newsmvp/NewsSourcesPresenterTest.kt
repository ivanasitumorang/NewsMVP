package com.example.newsmvp

import com.example.newsmvp.data.network.NewsApiService
import com.example.newsmvp.presentation.newssources.NewsSourcesContract
import com.example.newsmvp.presentation.newssources.NewsSourcesPresenter
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test

/**
 * Created by ivanaazuka on 9/26/2019.
 * Android Engineer
 **/

class NewsSourcesPresenterTest {
    private lateinit var mNewsSourcesPresenter: NewsSourcesPresenter
    private var mView: NewsSourcesContract.View = mock()
    private var mService: NewsApiService = mock()

    @Before
    fun setup() {
        mNewsSourcesPresenter = NewsSourcesPresenter()
        mNewsSourcesPresenter.setView(mView)
    }

    @Test
    fun fetchNewsSourcesSuccess() {
//        val source = Source(
//            "abc-news",
//            "ABC News",
//            "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
//            "https://abcnews.go.com",
//            "general",
//            "en",
//            "us"
//        )
//        val sources = listOf(source)
//        val result = SourcesResult("ok", sources)
//        val language = "en"
//        val country = "us"
//
//        val mCall = Calls.response(result)
//
//        doReturn(mCall)
//            .`when`(mService)
//            .getNewsSources(NewsApi.API_KEY, language, country)
//        mCall.execute()
//        mNewsSourcesPresenter.fetchNewsSources(language, country)
//
//        verify(mView).showProgressBar()
//        verify(mView).hideProgressBar()
    }
}