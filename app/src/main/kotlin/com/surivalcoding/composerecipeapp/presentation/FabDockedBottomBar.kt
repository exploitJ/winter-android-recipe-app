package com.surivalcoding.composerecipeapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.surivalcoding.composerecipeapp.presentation.navigation.TopLevelRoute
import com.surivalcoding.composerecipeapp.presentation.shared.CutoutShape
import com.surivalcoding.composerecipeapp.presentation.shared.boxShadow
import com.surivalcoding.composerecipeapp.presentation.shared.theme.AppColors


@Composable
fun <T : Any> NavigationBarWithFab(
    modifier: Modifier = Modifier,
    onFabClick: () -> Unit = {},
    contentData: List<TopLevelRoute<out T>>,
) {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    val fabSocketShape = remember { CutoutShape(cutoutWidth = 120.dp) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)

    ) {
        Surface(
            modifier = Modifier
                .padding(top = 16.dp)
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
                modifier = Modifier.fillMaxSize(),
            ) {
                for (index in 0..<contentData.size / 2) {
                    NavItem(selectedItem == index, contentData[index]) {
                        selectedItem = index
                    }
                }
                Spacer(Modifier.size(80.dp))
                for (index in contentData.size / 2..<contentData.size) {
                    val topLevelRoute = contentData[index]
                    NavItem(selectedItem == index, topLevelRoute) {
                        selectedItem = index
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
    isSelected: Boolean,
    topLevelRoute: TopLevelRoute<T>,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        colors = NavigationBarItemDefaults.colors().copy(
            unselectedIconColor = Color.Unspecified,
            selectedIndicatorColor = Color.Transparent,
            selectedIconColor = AppColors.primary100
        ),
        selected = isSelected,
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

@Preview
@Composable
private fun BottomBarWithFabPreview() {

    val list = listOf(
        TopLevelRoute("Home", App.Home, Icons.TwoTone.Home),
        TopLevelRoute("Home", App.Home, Icons.TwoTone.BookmarkBorder),
        TopLevelRoute("Home", App.Home, Icons.TwoTone.Notifications),
        TopLevelRoute("Home", App.Home, Icons.TwoTone.Person),
    )

    Scaffold(
        bottomBar = {
            NavigationBarWithFab(contentData = list)
        }
    ) { innerPadding ->
        Box(
            Modifier.consumeWindowInsets(innerPadding)
                .fillMaxSize()
                .background(color = Color.Yellow.copy(alpha = 0.2f))
        )

    }
}