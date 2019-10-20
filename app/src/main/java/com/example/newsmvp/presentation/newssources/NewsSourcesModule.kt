package com.example.newsmvp.presentation.newssources

import com.example.newsmvp.data.network.NewsApiService
import com.example.newsmvp.di.scope.ActivityScope
import com.example.newsmvp.external.AppSchedulerProvider
import com.example.newsmvp.presentation.common.navigationcontroller.ActivityNavigation
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class NewsSourcesModule {

//    @Provides
    @ActivityScope
    fun provideNewsSourcesPresenter(service: NewsApiService, schedulerProvider: AppSchedulerProvider, compositeDisposable: CompositeDisposable): NewsSourcesPresenter {
        return NewsSourcesPresenter(service, schedulerProvider, compositeDisposable)
    }

    @Provides
    @ActivityScope
    fun provideNavigationController(activity: NewsSourcesActivity) : ActivityNavigation
            = ActivityNavigation(activity)

}