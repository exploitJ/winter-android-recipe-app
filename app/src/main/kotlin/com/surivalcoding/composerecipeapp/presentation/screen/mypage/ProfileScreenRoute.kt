package com.surivalcoding.composerecipeapp.presentation.screen.mypage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Composable
fun ProfileScreenRoute(bottomPadding: Dp, viewModel: ProfileViewModel) {

    val myInfoUiState by viewModel.myInfoUiState.collectAsStateWithLifecycle()
    val savedPostsUiState by viewModel.savedPostsUiState.collectAsStateWithLifecycle()
    ProfileScreen(bottomPadding, myInfoUiState, savedPostsUiState)

}

@Serializable
data object ProfileRoute

fun NavGraphBuilder.profileScreenRoute(bottomPadding: Dp) {
    composable<ProfileRoute> {
        val viewModel = hiltViewModel<ProfileViewModel>()
        ProfileScreenRoute(
            bottomPadding,
            viewModel
        )
    }
}