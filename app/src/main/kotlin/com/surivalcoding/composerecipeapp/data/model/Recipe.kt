package com.surivalcoding.composerecipeapp.data.model

data class Recipe(
    val ingredients: List<Ingredient>,
    val instruction: List<Steps>,
    val starRating: Float,
    val servings: Int,
    val tags: Set<Tag>,
) {
    data class Steps(
        val title: String,
        val description: String,
    )
}

@JvmInline
value class Tag(val name: String)