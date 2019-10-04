package com.example.newsmvp.di.module

import android.app.Application
import android.content.Context
import com.example.newsmvp.external.AppSchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule (private val app: Application){
    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideSchedulerProvider() = AppSchedulerProvider()

    @Provides
    @Singleton
    fun provideCompositeDisposable() = CompositeDisposable()
}