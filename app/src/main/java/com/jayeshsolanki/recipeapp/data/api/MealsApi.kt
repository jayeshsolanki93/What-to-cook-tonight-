package com.jayeshsolanki.recipeapp.data.api

import com.jayeshsolanki.recipeapp.data.model.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {

    @GET("filter.php")
    suspend fun mealsByIngredient(
        @Query("i") ingredient: String,
        @Query("c") category: String = listOf("vegan", "vegetarian").random()
    ): MealsResponse

    @GET("lookup.php")
    suspend fun mealById(@Query("i") id: String): MealsResponse
}
