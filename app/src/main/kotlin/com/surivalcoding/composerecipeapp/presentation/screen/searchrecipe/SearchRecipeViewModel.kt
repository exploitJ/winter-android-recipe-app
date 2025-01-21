package com.surivalcoding.composerecipeapp.presentation.screen.searchrecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.surivalcoding.composerecipeapp.RecipeApplication
import com.surivalcoding.composerecipeapp.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchRecipeViewModel(
    val recipeRepository: RecipeRepository,
) : ViewModel() {
    private val _savedRecipes = MutableStateFlow(SearchRecipesState())
    val savedRecipes = _savedRecipes.asStateFlow()

    fun onSearchQuery(query: String) = viewModelScope.launch {
        val queries = query.split(' ', '\t').map { it.trim() }.toTypedArray()
        val found = recipeRepository.findBySearchTerm(*queries)
        _savedRecipes.update {
            it.copy(
                searchHits = found,
            )
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val recipeRepo = (this[APPLICATION_KEY] as RecipeApplication).recipeRepository
                SearchRecipeViewModel(
                    recipeRepository = recipeRepo,
                )
            }
        }
    }
}