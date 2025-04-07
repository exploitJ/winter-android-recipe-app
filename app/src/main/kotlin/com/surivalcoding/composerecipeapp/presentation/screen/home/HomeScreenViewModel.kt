package com.surivalcoding.composerecipeapp.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surivalcoding.composerecipeapp.domain.model.Post
import com.surivalcoding.composerecipeapp.domain.model.Recipe
import com.surivalcoding.composerecipeapp.domain.model.User
import com.surivalcoding.composerecipeapp.domain.repository.RecipeRepository
import com.surivalcoding.composerecipeapp.domain.repository.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val userRepository: UserInfoRepository,
) : ViewModel() {

    private val _newRecipeUiState =
        MutableStateFlow<NewRecipeUiState>(NewRecipeUiState.Loading)
    val newRecipeUiState = _newRecipeUiState.asStateFlow()

    private val _featuredRecipeUiState =
        MutableStateFlow<FeaturedRecipeUiState>(FeaturedRecipeUiState.Loading)
    val featuredRecipeUiState = _featuredRecipeUiState.asStateFlow()

    private val _userUiState = MutableStateFlow<UserUiState>(UserUiState.Anonymous)
    val userUiState = _userUiState.asStateFlow()

    init {
        viewModelScope.launch {
            val recipes = async { loadRecipes() }
            val recentPosts = async { loadRecentRecipes() }

            val recipesValue = recipes.await()
            val recentPostsValue = recentPosts.await()
            if (recipesValue.isEmpty()) {
                _featuredRecipeUiState.update {
                    FeaturedRecipeUiState.Empty
                }
            } else {
                _featuredRecipeUiState.update {
                    FeaturedRecipeUiState.Success(recipesValue)
                }
            }
            if (recentPostsValue.isEmpty()) {
                _newRecipeUiState.update {
                    NewRecipeUiState.Loading
                }
            } else {
                _newRecipeUiState.update {
                    NewRecipeUiState.Success(recentPostsValue)
                }

            }
        }
    }

    suspend fun loadRecipes() = recipeRepository.getAll()
    suspend fun loadRecentRecipes() = recipeRepository.getRecentPosts(20)

    suspend fun loadNewRecipes() =
        recipeRepository.getSorted<Instant> { a, b ->
            a.compareTo(b)
        }

}

sealed interface NewRecipeUiState {
    data object Loading : NewRecipeUiState
    data class Success(val recipes: List<Post<Recipe>>) : NewRecipeUiState
}

sealed interface FeaturedRecipeUiState {
    data object Loading : FeaturedRecipeUiState
    data object Empty : FeaturedRecipeUiState
    data class Success(val recipes: Set<Post<Recipe>>) : FeaturedRecipeUiState
}

sealed interface UserUiState {
    data object Anonymous : UserUiState
    data class Success(val user: User) : UserUiState
}