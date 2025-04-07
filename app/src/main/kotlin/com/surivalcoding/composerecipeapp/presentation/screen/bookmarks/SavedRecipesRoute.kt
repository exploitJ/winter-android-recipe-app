package com.surivalcoding.composerecipeapp.presentation.screen.bookmarks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Composable
fun SavedRecipesRoute(
    bottomPadding: Dp,
    viewModel: SavedRecipesViewModel,
) {
    val savedRecipesState by viewModel.savedRecipes.collectAsStateWithLifecycle()

    SavedRecipesScreen(
        state = savedRecipesState,
        bottomBarPadding = bottomPadding,
    )
}

@Serializable
data object SavedRecipesRoute

fun NavGraphBuilder.savedRecipesRoute(bottomPadding: Dp) {
    composable<SavedRecipesRoute> {
        val viewModel = hiltViewModel<SavedRecipesViewModel>()
        SavedRecipesRoute(bottomPadding, viewModel)
    }
}
