package com.example.newsmvp

import android.app.Activity
import android.app.Application
import com.example.newsmvp.di.component.AppComponent
import com.example.newsmvp.di.component.DaggerAppComponent
import com.example.newsmvp.di.module.NetworkModule
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class NewsApp : Application(), HasActivityInjector {

    companion object {
        @JvmStatic
        lateinit var instance: NewsApp

        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = createComponent()
        appComponent.inject(this)
    }

    private fun createComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .application(this)
            .networkModule(NetworkModule(applicationContext))
            .build()
    }

}