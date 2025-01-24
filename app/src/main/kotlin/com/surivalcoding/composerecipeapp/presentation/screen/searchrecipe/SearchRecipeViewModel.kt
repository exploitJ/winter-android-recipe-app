package com.surivalcoding.composerecipeapp.presentation.screen.searchrecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surivalcoding.composerecipeapp.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchRecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
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
}