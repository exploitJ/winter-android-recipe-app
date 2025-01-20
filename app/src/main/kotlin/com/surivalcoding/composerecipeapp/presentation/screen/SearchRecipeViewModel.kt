package com.surivalcoding.composerecipeapp.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SearchRecipeViewModel() : ViewModel() {

    fun onSearchQuery(query: String) = viewModelScope.launch {
        val queries = query.split(' ', '\t').map { it.trim() }.toTypedArray()
//        recipeRepository.findBySearchTerm(*queries)
    }


}