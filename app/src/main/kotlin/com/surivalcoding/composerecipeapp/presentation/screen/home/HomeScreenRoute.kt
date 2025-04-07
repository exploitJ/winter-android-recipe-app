package com.surivalcoding.composerecipeapp.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Composable
fun HomeScreenRoute(
    bottomPadding: Dp,
    viewModel: HomeScreenViewModel,
) {
    val userUiState by viewModel.userUiState.collectAsStateWithLifecycle()
    val recipeUiState by viewModel.featuredRecipeUiState.collectAsStateWithLifecycle()
    val newRecipeUiState by viewModel.newRecipeUiState.collectAsStateWithLifecycle()

    HomeScreen(
        bottomPadding = bottomPadding,
        userUiState = userUiState,
        newRecipeUiState = newRecipeUiState,
        featuredRecipeUiState = recipeUiState,
    )
}

@Serializable
data object HomeRoute

fun NavGraphBuilder.homeScreenRoute(bottomPadding: Dp) {
    composable<HomeRoute> {
        val viewModel = hiltViewModel<HomeScreenViewModel>()
        HomeScreenRoute(
            bottomPadding = bottomPadding,
            viewModel
        )
    }
}
