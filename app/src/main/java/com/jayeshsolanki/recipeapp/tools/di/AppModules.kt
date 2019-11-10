package com.jayeshsolanki.recipeapp.tools.di

import com.jayeshsolanki.recipeapp.data.api.MealsApi
import com.jayeshsolanki.recipeapp.data.source.DefaultRecipesRepository
import com.jayeshsolanki.recipeapp.data.source.RecipesRepository
import com.jayeshsolanki.recipeapp.recipedetails.RecipeDetailsViewModel
import com.jayeshsolanki.recipeapp.recipeslist.RecipesListViewModel
import com.jayeshsolanki.recipeapp.tools.NetworkManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {

    single<MealsApi> {
        NetworkManager.createService(androidContext(), NetworkManager.BASE_API_URL)
    }
}

val repositoryModule = module {

    single<RecipesRepository> { DefaultRecipesRepository(get()) }
}

val viewModelModule = module {

    viewModel { RecipesListViewModel(get()) }

    viewModel { RecipeDetailsViewModel(get()) }
}
