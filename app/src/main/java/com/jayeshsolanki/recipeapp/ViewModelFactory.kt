package com.jayeshsolanki.recipeapp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jayeshsolanki.recipeapp.recipedetails.RecipeDetailsViewModel
import com.jayeshsolanki.recipeapp.recipeslist.RecipesListViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val application: Application
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val recipesRepository = Injection.provideRecipesRepository(application)
        return when (modelClass) {
            RecipesListViewModel::class.java -> RecipesListViewModel(recipesRepository) as T
            RecipeDetailsViewModel::class.java -> RecipeDetailsViewModel(recipesRepository) as T
            else -> throw IllegalArgumentException("Unknown model class $modelClass")
        }
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
