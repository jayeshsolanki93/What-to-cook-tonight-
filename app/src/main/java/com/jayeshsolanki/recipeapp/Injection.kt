package com.jayeshsolanki.recipeapp

import android.app.Application
import com.jayeshsolanki.recipeapp.data.source.DefaultRecipesRepository
import com.jayeshsolanki.recipeapp.data.source.RecipesRepository

object Injection {

    fun provideRecipesRepository(context: Application): RecipesRepository {
        return DefaultRecipesRepository.getInstance((context as WhatToCookTonight).mealsApi)
    }
}
