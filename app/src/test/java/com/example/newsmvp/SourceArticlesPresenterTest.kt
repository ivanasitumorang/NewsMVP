package com.example.newsmvp

import com.example.newsmvp.data.entities.Article
import com.example.newsmvp.data.entities.Source
import com.example.newsmvp.data.entities.newsapi.ArticlesResult
import com.example.newsmvp.data.network.NewsApi
import com.example.newsmvp.data.network.NewsApiService
import com.example.newsmvp.external.SchedulerProvider
import com.example.newsmvp.presentation.articles.SourceArticlePresenter
import com.example.newsmvp.presentation.articles.SourceArticlesContract
import com.example.newsmvp.util.TestSchedulerProvider
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Flowable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test

/**
 * Created by ivanaazuka on 9/26/2019.
 * Android Engineer
 **/

class SourceArticlesPresenterTest {
    private lateinit var mSourcesArticlePresenter: SourceArticlePresenter
    private lateinit var mTestScheduler: TestScheduler
    private var mView : SourceArticlesContract.View = mock()
    private var mService : NewsApiService = mock()

    @Before
    fun setup(){
        mTestScheduler = TestScheduler()
        val mTestSchedulerProvider = TestSchedulerProvider(mTestScheduler)
        mSourcesArticlePresenter = SourceArticlePresenter(mTestSchedulerProvider)
        mSourcesArticlePresenter.setView(mView)
    }



    @Test
    fun fetchArticlesSuccess(){
        val source = Source(
            "abc-news",
            "ABC News",
            null,
            null,
            null,
            null,
            null
        )
        val article = Article(
            source,
            "Ivana",
            "Pembuatan makanan ayam",
            "Masakan sedap dari Indonesia",
            "https://google.com",
            "https://cdn.vox-cdn.com/thumbor/QGTESHfPDVUTY-jW8Qgipcvj7bM=/0x256:2079x1344/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/11539251/square_cash_01.jpeg",
            "2019-09-14T00:11:15Z",
            "Robinhood might have some competition\\r\\nImage: Square\\r\\nSquares Cash App already lets you do more than just pay back your friends for lunch the app started letting users buy and sell Bitcoin back in 2017. Soon, it may give you access to another kind of investme… [+594 chars]"
        )

        val articlesResult = ArticlesResult("ok", 1, listOf(article))

        source.id?.let {
            doReturn(Flowable.just(articlesResult))
                .`when`(mService)
                .getArticlesBySource(NewsApi.API_KEY, it)
        }

        source.id?.let { mSourcesArticlePresenter.fetchArticlesBySource(it) }
        mTestScheduler.triggerActions()
        verify(mView).showProgressBar()
        verify(mView).hideProgressBar()
    }

    @Test
    fun searchArticle_EmptyQueryWithSpaces(){
        val titleQuery = " "
        val article = Article(
            Source(
                "abc-news",
                "ABC News",
                "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
                "https://abcnews.go.com",
                "general",
                "en",
                "us"
            ), "", "Android", "", "", "", "", "")

        val articleList = listOf(article)
        mSourcesArticlePresenter.articleList = articleList
        mSourcesArticlePresenter.searchArticlesByTitle(titleQuery)
        verify(mView).showNoSearchResult(titleQuery)
    }

    @Test
    fun searchArticle_NoResult(){
        val titleQuery = "Cek"
        val article = Article(
            Source(
                "abc-news",
                "ABC News",
                "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
                "https://abcnews.go.com",
                "general",
                "en",
                "us"
            ), "", "Android", "", "", "", "", "")

        val articleList = listOf(article)
        mSourcesArticlePresenter.articleList = articleList
        mSourcesArticlePresenter.searchArticlesByTitle(titleQuery)
        verify(mView).showNoSearchResult(titleQuery)
    }

    @Test
    fun searchArticle_AvailableResult(){
        val titleQuery = "Android"
        val article = Article(
            Source(
                "abc-news",
                "ABC News",
                "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
                "https://abcnews.go.com",
                "general",
                "en",
                "us"
            ), "", "Android", "", "", "", "", "")

        val articleList = listOf(article)
        mSourcesArticlePresenter.articleList = articleList
        mSourcesArticlePresenter.fetchArticlesBySource("")
        mSourcesArticlePresenter.searchArticlesByTitle(titleQuery)
        verify(mView).hideNoSearchResult()
    }
}