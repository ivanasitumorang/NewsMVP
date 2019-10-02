package com.example.newsmvp.presentation.common

/**
 * Created by ivanaazuka on 10/1/2019.
 * Android Engineer
 **/

interface BaseView {
    fun setPresenter(presenter: BasePresenter<*>)
}