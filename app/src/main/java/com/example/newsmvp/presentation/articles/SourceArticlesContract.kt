package com.example.newsmvp.presentation.articles

import com.example.newsmvp.data.entities.Article

class SourceArticlesContract {
    interface View {
        fun initializeData(sourceId: String)
        fun setupUI()
        fun showProgressBar()
        fun hideProgressBar()
        fun setArticles(articles: List<Article>)
        fun setRecyclerView()
        fun setupToolbar(sourceName: String)
        fun setNavigation()
    }

    interface UserActionListener {
        fun fetchArticlesBySource(sourceId: String)
        fun searchArticlesByTitle(sourceId: String)
    }
}