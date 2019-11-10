package com.jayeshsolanki.recipeapp.recipedetails

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

class RecipeDetailsViewModel(
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    private val _item = MutableLiveData<Recipe>()
    val item: LiveData<Recipe> = _item

    val command: SingleLiveEvent<BaseCommand> = SingleLiveEvent()

    fun start(recipeId: String) {
        viewModelScope.launch {
            val recipeDetailsResult = recipesRepository.getRecipeDetails(recipeId)
            if (recipeDetailsResult is Success) {
                val meal = recipeDetailsResult.data
                _item.value = meal.getRecipe()
            } else {
                command.value = BaseCommand.Error("Sorry! There was some error fetching this recipe")
                Timber.w((recipeDetailsResult as Error).message)
            }
        }
    }
}
