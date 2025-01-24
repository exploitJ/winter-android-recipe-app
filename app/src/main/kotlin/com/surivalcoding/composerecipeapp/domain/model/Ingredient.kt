package com.surivalcoding.composerecipeapp.domain.model

data class Ingredient(
    val name: String,
    val shortName: String,
    val thumbnail: Media.Image,
    val amountInGrams: Int,
)
