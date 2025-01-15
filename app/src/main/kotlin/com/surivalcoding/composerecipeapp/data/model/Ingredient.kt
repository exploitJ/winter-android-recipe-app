package com.surivalcoding.composerecipeapp.data.model

data class Ingredient(
    val name: String,
    val shortName: String,
    val thumbnail: Media.Image,
    val amountInGrams: Int,
)
