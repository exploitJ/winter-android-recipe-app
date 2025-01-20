package com.surivalcoding.composerecipeapp.presentation.screen.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.surivalcoding.composerecipeapp.RecipeApplication
import com.surivalcoding.composerecipeapp.data.model.UserId
import com.surivalcoding.composerecipeapp.data.repository.RecipeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class SavedRecipesViewModel(
    val recipeRepository: RecipeRepository,
) : ViewModel() {
    private val _savedRecipes =
        MutableStateFlow(SavedRecipesState(savedRecipes = emptyList(), isLoading = true))
    val savedRecipes = _savedRecipes.asStateFlow()

    init {
        populateSavedRecipes()
    }

    private fun populateSavedRecipes() = viewModelScope.launch {
        val posts = recipeRepository.getSavedRecipes(UserId(UUID.randomUUID()))
        delay(1000)
        _savedRecipes.update {
            it.copy(
                savedRecipes = posts,
                isLoading = false,
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val recipeRepo = (this[APPLICATION_KEY] as RecipeApplication).recipeRepository
                SavedRecipesViewModel(
                    recipeRepository = recipeRepo,
                )
            }
        }
    }
}