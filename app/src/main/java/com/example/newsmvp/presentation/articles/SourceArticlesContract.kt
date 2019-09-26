package com.example.newsmvp.presentation.articles

import com.example.newsmvp.data.entities.Article

class SourceArticlesContract {
    interface View {
        fun initializeData(sourceId: String)
        fun setupUI()
        fun showProgressBar()
        fun hideProgressBar()
        fun setArticles(articles: List<Article>?)
        fun setRecyclerView()
        fun setupToolbar(sourceName: String)
        fun setNavigation()
        fun showNoSearchResult(query: String?)
        fun hideNoSearchResult()
    }

    interface Presenter {
        fun fetchArticlesBySource(sourceId: String)
        fun searchArticlesByTitle(title: String?)
        fun setView(view: View)
        fun cancelFetchArticles()
    }
}