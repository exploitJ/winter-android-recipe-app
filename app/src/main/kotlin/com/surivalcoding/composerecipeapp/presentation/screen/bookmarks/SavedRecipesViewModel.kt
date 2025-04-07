package com.surivalcoding.composerecipeapp.presentation.screen.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surivalcoding.composerecipeapp.domain.repository.RecipeRepository
import com.surivalcoding.composerecipeapp.domain.repository.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedRecipesViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
    private val recipeRepository: RecipeRepository,
) : ViewModel() {
    private val _savedRecipes =
        MutableStateFlow(SavedRecipesState(savedRecipes = emptyList(), isLoading = true))
    val savedRecipes = _savedRecipes.asStateFlow()

    init {
        populateSavedRecipes()
    }

    private fun populateSavedRecipes() = viewModelScope.launch {
        val user = userInfoRepository.getCurrentUser()
        val posts = recipeRepository.getSavedRecipes(user.id)
        _savedRecipes.update {
            it.copy(
                savedRecipes = posts,
                isLoading = false,
            )
        }
    }
}