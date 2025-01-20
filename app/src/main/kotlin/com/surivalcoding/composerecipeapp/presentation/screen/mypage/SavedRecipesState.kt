package com.surivalcoding.composerecipeapp.presentation.screen.mypage

import com.surivalcoding.composerecipeapp.data.model.Post
import com.surivalcoding.composerecipeapp.data.model.Recipe

data class SavedRecipesState(
    val savedRecipes: List<Post<Recipe>> = emptyList(),
    val isLoading: Boolean = true,
)
