package com.example.newsmvp

import com.example.newsmvp.data.entities.Article
import com.example.newsmvp.data.entities.Source
import com.example.newsmvp.presentation.articles.SourceArticlePresenter
import com.example.newsmvp.presentation.articles.SourceArticlesContract
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

/**
 * Created by ivanaazuka on 9/26/2019.
 * Android Engineer
 **/

class SourceArticlesPresenterTest {
    private lateinit var mSourcesArticlePresenter: SourceArticlePresenter
    private var mView : SourceArticlesContract.View = mock()

    @Before
    fun setup(){
        mSourcesArticlePresenter = SourceArticlePresenter()
        mSourcesArticlePresenter.setView(mView)
    }



    @Test
    fun fetchArticles(){

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