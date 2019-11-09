package com.jayeshsolanki.recipeapp.data.source

import com.jayeshsolanki.recipeapp.data.model.Meal
import com.jayeshsolanki.recipeapp.data.Result

interface RecipesRepository {

    suspend fun getRecipesList(ingredient: String): Result<List<Meal>>

    suspend fun getRecipeDetails(mealId: String): Result<Meal>
}
