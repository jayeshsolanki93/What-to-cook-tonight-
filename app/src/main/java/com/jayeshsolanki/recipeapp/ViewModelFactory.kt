package com.jayeshsolanki.recipeapp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jayeshsolanki.recipeapp.recipeslist.RecipesListViewModel

class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == RecipesListViewModel::class.java) {
            return RecipesListViewModel(Injection.provideRecipesRepository(application)) as T
        }
        throw IllegalArgumentException("Unknown model class $modelClass")
    }

    companion object {
        // For singleton instantiation
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Application): ViewModelFactory {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(context).also { INSTANCE = it }
            }
        }
    }
}
