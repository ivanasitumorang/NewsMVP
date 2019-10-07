package com.example.newsmvp.di.component

import android.app.Application
import com.example.newsmvp.NewsApp
import com.example.newsmvp.di.module.AppModule
import com.example.newsmvp.di.module.NetworkModule
import com.example.newsmvp.di.module.builder.ActivityBuilder
import com.example.newsmvp.presentation.articles.SourceArticlesActivity
import com.example.newsmvp.presentation.articles.SourceArticlesModule
import com.example.newsmvp.presentation.newssources.NewsSourcesActivity
import com.example.newsmvp.presentation.newssources.NewsSourcesModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AndroidInjectionModule::class),
    (NetworkModule::class),
    (AppModule::class),
    (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder
        fun networkModule(networkModule: NetworkModule) : Builder
        fun build(): AppComponent
    }

    fun inject(app: NewsApp)
}