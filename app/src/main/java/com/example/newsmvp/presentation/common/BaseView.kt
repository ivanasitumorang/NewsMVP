package com.example.newsmvp.presentation.common

interface BaseView {
    fun setPresenter(presenter: BasePresenter<*>)
}