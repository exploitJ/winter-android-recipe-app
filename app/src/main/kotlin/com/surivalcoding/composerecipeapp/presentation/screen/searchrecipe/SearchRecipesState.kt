package com.surivalcoding.composerecipeapp.presentation.screen.searchrecipe

import com.surivalcoding.composerecipeapp.data.model.Post
import com.surivalcoding.composerecipeapp.data.model.Recipe

data class SearchRecipesState(
    val recipesList: List<Post<Recipe>> = emptyList(),
    val recentSearchHits: List<Post<Recipe>> = emptyList(),
    val searchHits: List<Post<Recipe>> = emptyList(),
) {
    val hitCount: Int
        get() = searchHits.size
}
