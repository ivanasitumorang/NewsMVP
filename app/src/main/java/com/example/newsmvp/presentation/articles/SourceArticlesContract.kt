package com.example.newsmvp.presentation.articles

import com.example.newsmvp.data.entities.Article
import com.example.newsmvp.data.entities.Source

class SourceArticlesContract {
    interface View {
        fun initializeData(source: Source)
        fun setupUI()
        fun showProgressBar()
        fun hideProgressBar()
        fun setArticles(articles: List<Article>)
        fun setRecyclerView()
        fun setupToolbar(sourceName: String)
        fun setNavigation()
    }

    interface UserActionListener {
        fun fetchArticlesBySource(source: Source)
        fun searchArticlesByTitle(sourceId: String)
    }
}