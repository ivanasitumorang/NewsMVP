package com.example.newsmvp

import android.app.Application
import com.example.newsmvp.di.component.AppComponent
import com.example.newsmvp.di.component.DaggerAppComponent
import com.example.newsmvp.di.module.AppModule

class NewsApp : Application(){
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: NewsApp): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()

}