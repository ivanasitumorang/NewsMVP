package com.example.newsmvp.presentation.main

import com.example.newsmvp.data.entities.Source

class MainContract {
    interface View {
        fun initializeData()
        fun setupUI()
        fun setRecyclerView()
        fun setNewsSources(sources: List<Source>)
        fun setNavigation()
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface UserActionListener {
        fun fetchNewsSources(language: String, country: String)
    }
}