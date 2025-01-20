package com.surivalcoding.composerecipeapp.presentation.screen

import com.surivalcoding.composerecipeapp.data.model.Post
import com.surivalcoding.composerecipeapp.data.model.Recipe

data class SearchRecipesState(
    val recentSearchHits: List<Post<Recipe>> = emptyList(),
    val searchResults: List<Post<Recipe>> = emptyList(),
)
