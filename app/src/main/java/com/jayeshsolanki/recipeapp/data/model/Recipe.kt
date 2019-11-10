package com.jayeshsolanki.recipeapp.data.model

data class Recipe(
    val id: String,
    val title: String,
    val image: String,
    var category: String = "",
    var from: String = "",
    var instructions: String = "",
    var ingredients: List<String> = emptyList(),
    var measurements: List<String> = emptyList()
)
