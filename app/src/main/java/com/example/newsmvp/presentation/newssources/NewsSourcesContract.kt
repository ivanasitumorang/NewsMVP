package com.example.newsmvp.presentation.newssources

import com.example.newsmvp.data.entities.Source

class NewsSourcesContract {
    interface View {
        fun initializeData()
        fun setupUI()
        fun setRecyclerView()
        fun setNewsSources(sources: List<Source>)
        fun setNavigation()
        fun showProgressBar()
        fun hideProgressBar()
        fun setupToolbar()
    }

    interface Presenter {
        fun fetchNewsSources(language: String, country: String)
        fun setView(view: View)
    }
}