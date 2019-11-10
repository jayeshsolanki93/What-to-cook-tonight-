package com.jayeshsolanki.recipeapp.recipeslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayeshsolanki.recipeapp.data.Result.Success
import com.jayeshsolanki.recipeapp.data.Result.Error
import com.jayeshsolanki.recipeapp.data.model.Recipe
import com.jayeshsolanki.recipeapp.data.model.getRecipe
import com.jayeshsolanki.recipeapp.data.source.RecipesRepository
import com.jayeshsolanki.recipeapp.utils.BaseCommand
import com.jayeshsolanki.recipeapp.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class RecipesListViewModel(
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    private val availableIngredients = listOf("avocado", "tofu", "chili", "potato", "salt", "banana")

    val command: SingleLiveEvent<BaseCommand> = SingleLiveEvent()

    private val _items = MutableLiveData<List<Recipe>>().apply { value = emptyList() }
    val items: LiveData<List<Recipe>> = _items

    init {
        loadRecipesList()
    }

    private fun loadRecipesList() {
        viewModelScope.launch {
            // Randomly select an ingredient.
            val recipesListResult = recipesRepository.getRecipesList(availableIngredients.random())
            if (recipesListResult is Success) {
                val meals = recipesListResult.data
                val recipes = meals.map { it.getRecipe() }
                _items.value = recipes
                command.value = BaseCommand.Success("${recipesListResult.data.size} recipes found")
            } else {
                _items.value = emptyList()
                command.value = BaseCommand.Error("Sorry! Cannot fetch recipes")
                Timber.w((recipesListResult as Error).message)
            }
        }
    }
}
