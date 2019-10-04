package com.example.newsmvp.presentation.newssources

import com.example.newsmvp.data.network.NewsApiService
import com.example.newsmvp.external.AppSchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NewsSourcesModule {

    @Provides
    @Singleton
    fun provideNewsSourcesPresenter(service: NewsApiService, schedulerProvider: AppSchedulerProvider): NewsSourcesPresenter {
        return NewsSourcesPresenter(service, schedulerProvider)
    }
}