package com.jayeshsolanki.recipeapp.data.model

import com.google.gson.annotations.SerializedName

data class MealsResponse(
    @SerializedName("meals") var mealsList: List<Meal>
)
