package com.example.newsmvp.presentation.articles

import com.example.newsmvp.data.network.NewsApiService
import com.example.newsmvp.di.scope.ActivityScope
import com.example.newsmvp.external.AppSchedulerProvider
import com.example.newsmvp.presentation.common.navigationcontroller.ActivityNavigation
import com.example.newsmvp.presentation.newssources.NewsSourcesActivity
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class SourceArticlesModule {

    @ActivityScope
    fun provideSourceArticlesPresenter(service: NewsApiService, schedulerProvider: AppSchedulerProvider, compositeDisposable: CompositeDisposable): SourceArticlePresenter {
        return SourceArticlePresenter(service, schedulerProvider, compositeDisposable)
    }

    @Provides
    @ActivityScope
    fun provideNavigationController(activity: SourceArticlesActivity) : ActivityNavigation
            = ActivityNavigation(activity)
}