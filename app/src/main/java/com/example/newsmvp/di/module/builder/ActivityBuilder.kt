package com.example.newsmvp.di.module.builder

import com.example.newsmvp.di.scope.ActivityScope
import com.example.newsmvp.presentation.articles.SourceArticlesActivity
import com.example.newsmvp.presentation.articles.SourceArticlesModule
import com.example.newsmvp.presentation.newssources.NewsSourcesActivity
import com.example.newsmvp.presentation.newssources.NewsSourcesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [(NewsSourcesModule::class)])
    internal abstract fun bindNewsSourcesActivity(): NewsSourcesActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(SourceArticlesModule::class)])
    internal abstract fun bindSourceArticlesActivity(): SourceArticlesActivity
}