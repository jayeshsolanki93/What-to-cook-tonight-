package com.jayeshsolanki.recipeapp.data.source

import com.jayeshsolanki.recipeapp.data.Result
import com.jayeshsolanki.recipeapp.data.Result.Success
import com.jayeshsolanki.recipeapp.data.Result.Error
import com.jayeshsolanki.recipeapp.data.api.MealsApi
import com.jayeshsolanki.recipeapp.data.model.Meal
import java.io.IOException

class DefaultRecipesRepository(private val mealsApi: MealsApi) : RecipesRepository {

    override suspend fun getRecipesList(ingredient: String): Result<List<Meal>> {
        return try {
            val response = mealsApi.mealsByIngredient(ingredient)
            Success(response.mealsList)
        } catch (e: IOException) {
            Error(e, "Failed to complete network request")
        }
    }

    override suspend fun getRecipeDetails(mealId: String): Result<Meal> {
        return try {
            val response = mealsApi.mealById(mealId)
            Success(response.mealsList.first())
        } catch (e: IOException) {
            Error(e, "Failed to complete network request")
        }
    }

    companion object {
        // For singleton instantiation
        @Volatile
        private var INSTANCE: RecipesRepository? = null

        fun getInstance(mealsApi: MealsApi): RecipesRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: DefaultRecipesRepository(mealsApi).also { INSTANCE = it }
            }
        }
    }
}
