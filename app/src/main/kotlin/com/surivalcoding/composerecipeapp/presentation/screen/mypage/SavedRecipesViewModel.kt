package com.surivalcoding.composerecipeapp.presentation.screen.mypage

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SavedRecipesViewModel : ViewModel() {
    private val _savedRecipes = MutableStateFlow(SavedRecipesState())
    val savedRecipes = _savedRecipes.asStateFlow()
}