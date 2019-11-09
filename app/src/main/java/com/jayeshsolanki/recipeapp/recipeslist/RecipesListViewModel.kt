package com.jayeshsolanki.recipeapp.recipeslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayeshsolanki.recipeapp.data.Result.Success
import com.jayeshsolanki.recipeapp.data.Result.Error
import com.jayeshsolanki.recipeapp.data.model.Meal
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

    private val _items = MutableLiveData<List<Meal>>().apply { value = emptyList() }
    val items: LiveData<List<Meal>> = _items

    init {
        loadRecipesList()
    }

    fun loadRecipesList() {
        viewModelScope.launch {
            // TODO: Show loading
            // Randomly select an ingredient.
            val recipesListResult = recipesRepository.getRecipesList(availableIngredients.random())
            if (recipesListResult is Success) {
                _items.value = recipesListResult.data
                command.value = BaseCommand.Success("${recipesListResult.data.size} recipes found")
            } else {
                _items.value = emptyList()
                command.value = BaseCommand.Error("Sorry! Cannot fetch recipes")
                Timber.w((recipesListResult as Error).message)
            }
        }
    }
}
