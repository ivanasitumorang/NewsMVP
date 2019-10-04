package com.example.newsmvp.di.component

import com.example.newsmvp.di.module.AppModule
import com.example.newsmvp.di.module.NetworkModule
import com.example.newsmvp.presentation.articles.SourceArticlesActivity
import com.example.newsmvp.presentation.articles.SourceArticlesModule
import com.example.newsmvp.presentation.newssources.NewsSourcesActivity
import com.example.newsmvp.presentation.newssources.NewsSourcesModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (NetworkModule::class),
    (AppModule::class),
    (NewsSourcesModule::class),
    (SourceArticlesModule::class)])
interface AppComponent {
    fun inject(newsSourcesActivity: NewsSourcesActivity)
    fun inject(sourceArticlesActivity: SourceArticlesActivity)
}