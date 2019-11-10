package com.jayeshsolanki.recipeapp

import android.app.Application
import com.facebook.stetho.Stetho
import com.jayeshsolanki.recipeapp.tools.di.apiModule
import com.jayeshsolanki.recipeapp.tools.di.repositoryModule
import com.jayeshsolanki.recipeapp.tools.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class WhatToCookTonight : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        startKoin {
            androidContext(this@WhatToCookTonight)
            modules(listOf(apiModule, repositoryModule, viewModelModule))
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
