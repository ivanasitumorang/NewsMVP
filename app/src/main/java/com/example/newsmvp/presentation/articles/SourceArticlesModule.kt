package com.example.newsmvp.presentation.articles

import com.example.newsmvp.data.network.NewsApiService
import com.example.newsmvp.external.AppSchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class SourceArticlesModule {

    @Provides
    @Singleton
    fun provideSourceArticlesPresenter(service: NewsApiService, schedulerProvider: AppSchedulerProvider, compositeDisposable: CompositeDisposable): SourceArticlePresenter {
        return SourceArticlePresenter(service, schedulerProvider, compositeDisposable)
    }

}