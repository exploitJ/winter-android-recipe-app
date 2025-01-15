package com.surivalcoding.composerecipeapp.data.model

data class Ingredient(
    val name: String,
    val shortName: String,
    val thumbnail: Media,
    val amountInGrams: Int,
)
