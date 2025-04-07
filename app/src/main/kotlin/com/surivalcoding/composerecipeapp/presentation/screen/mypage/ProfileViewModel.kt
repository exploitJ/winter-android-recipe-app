package com.surivalcoding.composerecipeapp.presentation.screen.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surivalcoding.composerecipeapp.domain.model.Post
import com.surivalcoding.composerecipeapp.domain.model.PostId
import com.surivalcoding.composerecipeapp.domain.model.Recipe
import com.surivalcoding.composerecipeapp.domain.model.User
import com.surivalcoding.composerecipeapp.domain.repository.RecipeRepository
import com.surivalcoding.composerecipeapp.domain.repository.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
    private val recipeRepository: RecipeRepository,
) : ViewModel() {
    private val _myInfoUiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val myInfoUiState = _myInfoUiState.asStateFlow()

    private val _savedPostsUiState = MutableStateFlow<SavedPostsUiState>(SavedPostsUiState.Loading)
    val savedPostsUiState = _savedPostsUiState.asStateFlow()

    init {
        viewModelScope.launch {
            val myInfo = getMyInfo()
            when (myInfo) {
                ProfileUiState.Loading -> {}
                is ProfileUiState.Success -> {
                    getSavedPosts(myInfo.myInfo.savedPosts)
                }
            }

        }

    }

    suspend fun getMyInfo(): ProfileUiState {
        return _myInfoUiState.updateAndGet {
            val me = userInfoRepository.getCurrentUser()
            ProfileUiState.Success(myInfo = me)
        }
    }

    suspend fun getSavedPosts(postIds: List<PostId>) {
        _savedPostsUiState.update {
            val savedPosts = recipeRepository.getAll()
                .filter { post -> postIds.contains(post.id) }
            SavedPostsUiState.Success(recipes = savedPosts)
        }
    }
}

sealed interface ProfileUiState {
    data object Loading : ProfileUiState
    data class Success(val myInfo: User) : ProfileUiState
}

sealed interface SavedPostsUiState {
    data object Loading : SavedPostsUiState
    data class Success(val recipes: List<Post<Recipe>>) : SavedPostsUiState
}
