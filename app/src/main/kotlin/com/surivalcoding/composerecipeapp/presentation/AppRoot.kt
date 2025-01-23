package com.surivalcoding.composerecipeapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.BookmarkBorder
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Notifications
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.surivalcoding.composerecipeapp.presentation.navigation.TopLevelRoute
import com.surivalcoding.composerecipeapp.presentation.screen.mypage.SavedRecipesScreen
import com.surivalcoding.composerecipeapp.presentation.screen.mypage.SavedRecipesViewModel

@Composable
fun AppRoot() {

    val navController = rememberNavController()
    val list = listOf(
        TopLevelRoute("Home", App.Home, icon = Icons.TwoTone.Home, onNavigation = {}),
        TopLevelRoute(
            "SavedRecipes",
            App.SavedRecipes,
            icon = Icons.TwoTone.BookmarkBorder,
            onNavigation = {}),
        TopLevelRoute(
            "Notification",
            App.Notifications,
            icon = Icons.TwoTone.Notifications,
            onNavigation = {}),
        TopLevelRoute("Profile", App.Profile, icon = Icons.TwoTone.Person, onNavigation = {}),
    )

    Scaffold(
        bottomBar = { NavigationBarWithFab(contentData = list) },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = App.Home
        ) {
            main(navController)
            composable<App.Home> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .background(
                                color = Color.Red
                            )
                            .align(Alignment.BottomCenter)
                            .padding(bottom = innerPadding.calculateBottomPadding())
                    ) {

                    }
                }
            }

        }
    }
}

@Preview
@Composable
private fun AppRootPreview() {
    AppRoot()
}

fun NavGraphBuilder.main(navController: NavHostController) {
    navigation<App>(startDestination = App.Home) {
        composable<App.Home> {

        }
        composable<App.SavedRecipes> {
            val viewModel =
                viewModel<SavedRecipesViewModel>(factory = SavedRecipesViewModel.Factory)
            val state by viewModel.savedRecipes.collectAsStateWithLifecycle()

            SavedRecipesScreen(state = state)
        }
    }
}
