package com.example.newsmvp.presentation.common

interface IBasePresenter<in V : BaseView>{
    fun attachView(view: V)
    fun detachView()
}