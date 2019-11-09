package com.jayeshsolanki.recipeapp.data.model

import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("idMeal") var id: String,
    @SerializedName("strMeal") var title: String,
    @SerializedName("strMealThumb") var image: String
)
