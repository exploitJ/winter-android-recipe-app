package com.surivalcoding.composerecipeapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.twotone.BookmarkBorder
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Notifications
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.surivalcoding.composerecipeapp.presentation.screen.home.HomeRoute
import com.surivalcoding.composerecipeapp.presentation.shared.CutoutShape
import com.surivalcoding.composerecipeapp.presentation.shared.boxShadow
import com.surivalcoding.composerecipeapp.presentation.shared.theme.AppColors

data class TopLevelNavigationItem<T : Any>(
    val name: String,
    val route: T,
    val icon: ImageVector,
)

@Composable
fun <T : Any> NavigationBarWithFab(
    modifier: Modifier = Modifier,
    onFabClick: () -> Unit = {},
    navController: NavController,
    contentData: List<TopLevelNavigationItem<out T>>,
) {
    val fabSocketShape = remember { CutoutShape(cutoutWidth = 120.dp) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Box {
        Surface(
            modifier = Modifier
                .padding(top = 14.dp)
                .align(Alignment.BottomEnd)
                .boxShadow(
                    shape = fabSocketShape,
                    color = Color.Black.copy(alpha = 0.28f),
                    blurRadius = 8.dp,
                ),
            shape = fabSocketShape,
        ) {
            NavigationBar(
                containerColor = Color.White,
            ) {
                for (index in contentData.indices) {
                    if (index == contentData.size / 2) {
                        Spacer(Modifier.size(80.dp))
                    }

                    val topLevelRoute = contentData[index]
                    NavItem(selected = currentDestination?.hierarchy?.any {
                        it.hasRoute(
                            topLevelRoute.route::class
                        )
                    } == true,
                        topLevelRoute) {
                        navController.navigate(topLevelRoute.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                                inclusive = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.TopCenter),
            containerColor = AppColors.primary100,
            elevation = FloatingActionButtonDefaults.loweredElevation(),
            shape = CircleShape,
            onClick = onFabClick,
        ) {
            Icon(
                tint = Color.White,
                imageVector = Icons.Filled.Add,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun <T : Any> RowScope.NavItem(
    selected: Boolean,
    topLevelRoute: TopLevelNavigationItem<T>,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        colors = NavigationBarItemDefaults.colors().copy(
            unselectedIconColor = Color.Unspecified,
            selectedIndicatorColor = Color.Transparent,
            selectedIconColor = AppColors.primary100
        ),
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = topLevelRoute.icon,
                contentDescription = topLevelRoute.name,
            )
        },
    )
}

@Preview(showSystemUi = true)
@Composable
private fun BottomBarWithFabPreview() {

    val list = listOf(
        TopLevelNavigationItem("Home", HomeRoute, Icons.TwoTone.Home),
        TopLevelNavigationItem("Home", HomeRoute, Icons.TwoTone.BookmarkBorder),
        TopLevelNavigationItem("Home", HomeRoute, Icons.TwoTone.Notifications),
        TopLevelNavigationItem("Home", HomeRoute, Icons.TwoTone.Person),
    )

    Scaffold(
        bottomBar = {
            NavigationBarWithFab(
                contentData = list,
                navController = rememberNavController(),
            )
        }
    ) { innerPadding ->
        Box(
            Modifier
                .consumeWindowInsets(innerPadding)
                .fillMaxSize()
                .background(color = Color.Yellow.copy(alpha = 0.2f))
        )

    }
}