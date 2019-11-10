package com.jayeshsolanki.recipeapp

import android.app.Application
import com.facebook.stetho.Stetho
import com.jayeshsolanki.recipeapp.data.api.MealsApi
import com.jayeshsolanki.recipeapp.tools.NetworkManager
import timber.log.Timber

class WhatToCookTonight : Application() {

    lateinit var mealsApi: MealsApi
        internal set

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        initializeApi()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initializeApi() {
        val retrofit = NetworkManager.retrofit(this, BASE_API_URL)
        mealsApi = retrofit.create(MealsApi::class.java)
    }

    companion object {
        private const val BASE_API_URL = "https://www.themealdb.com/api/json/v1/1/"
    }
}
