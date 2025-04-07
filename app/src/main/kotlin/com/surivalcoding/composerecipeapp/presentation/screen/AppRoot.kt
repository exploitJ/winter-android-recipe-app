package com.surivalcoding.composerecipeapp.presentation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.BookmarkBorder
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Notifications
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.surivalcoding.composerecipeapp.presentation.NavigationBarWithFab
import com.surivalcoding.composerecipeapp.presentation.TopLevelNavigationItem
import com.surivalcoding.composerecipeapp.presentation.screen.bookmarks.SavedRecipesRoute
import com.surivalcoding.composerecipeapp.presentation.screen.bookmarks.savedRecipesRoute
import com.surivalcoding.composerecipeapp.presentation.screen.home.HomeRoute
import com.surivalcoding.composerecipeapp.presentation.screen.home.homeScreenRoute
import com.surivalcoding.composerecipeapp.presentation.screen.mypage.ProfileRoute
import com.surivalcoding.composerecipeapp.presentation.screen.mypage.profileScreenRoute
import com.surivalcoding.composerecipeapp.presentation.screen.notification.NotificationRoute
import com.surivalcoding.composerecipeapp.presentation.screen.notification.notificationScreenRoute
import com.surivalcoding.composerecipeapp.presentation.screen.signin.SignInRoute
import kotlinx.serialization.Serializable

private val navigationItems = listOf(
    TopLevelNavigationItem(
        "Home",
        HomeRoute,
        icon = Icons.TwoTone.Home,
    ),
    TopLevelNavigationItem(
        "SavedRecipes",
        SavedRecipesRoute,
        icon = Icons.TwoTone.BookmarkBorder,
    ),
    TopLevelNavigationItem(
        "Notification",
        NotificationRoute,
        icon = Icons.TwoTone.Notifications,
    ),
    TopLevelNavigationItem(
        "Profile",
        ProfileRoute,
        icon = Icons.TwoTone.Person,
    )
)

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBarWithFab(
                contentData = navigationItems.toList(),
                navController = navController
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = TopLevel.Navigation,
        ) {
            val bottomBarPadding = innerPadding.calculateBottomPadding()
            navigation<TopLevel.Navigation>(startDestination = HomeRoute) {
                homeScreenRoute(
                    bottomPadding = bottomBarPadding
                )
                savedRecipesRoute(
                    bottomPadding = bottomBarPadding
                )
                notificationScreenRoute(
                    bottomPadding = bottomBarPadding
                )
                profileScreenRoute(
                    bottomPadding = bottomBarPadding
                )
            }
        }
    }
}

@Serializable
data object TopLevel {
    @Serializable
    data object Navigation
}


fun NavGraphBuilder.topLevelBase() {
    composable<TopLevel> {
        AppNavHost()
    }
}

fun NavController.navigateToApp() {
    navigate(TopLevel, navOptions = navOptions {
        popUpTo<SignInRoute> {
            inclusive = true
        }
    })
}