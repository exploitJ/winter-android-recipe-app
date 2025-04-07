package com.surivalcoding.composerecipeapp.presentation.screen.notification

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Composable
fun NotificationScreenRoute(
    bottomPadding: Dp,
    viewModel: NotificationViewModel,
) {
    val notificationUiState by viewModel.notificationUiState.collectAsStateWithLifecycle()

    NotificationScreen(
        bottomPadding = bottomPadding,
        notificationUiState
    )

}

@Serializable
data object NotificationRoute

fun NavGraphBuilder.notificationScreenRoute(bottomPadding: Dp) {
    composable<NotificationRoute> {
        val viewModel = hiltViewModel<NotificationViewModel>()
        NotificationScreenRoute(bottomPadding, viewModel)
    }
}