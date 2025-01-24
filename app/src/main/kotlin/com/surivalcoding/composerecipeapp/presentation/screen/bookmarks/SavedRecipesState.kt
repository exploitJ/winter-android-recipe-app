package com.surivalcoding.composerecipeapp.presentation.screen.bookmarks

import com.surivalcoding.composerecipeapp.domain.model.Post
import com.surivalcoding.composerecipeapp.domain.model.Recipe

data class SavedRecipesState(
    val savedRecipes: List<Post<Recipe>> = emptyList(),
    val isLoading: Boolean = true,
)
