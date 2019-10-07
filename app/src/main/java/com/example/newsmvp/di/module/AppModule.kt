package com.example.newsmvp.di.module

import android.app.Application
import android.content.Context
import com.example.newsmvp.external.AppSchedulerProvider
import com.example.newsmvp.external.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideSchedulerProvider() : SchedulerProvider = AppSchedulerProvider()

    @Provides
    @Singleton
    fun provideCompositeDisposable() = CompositeDisposable()
}