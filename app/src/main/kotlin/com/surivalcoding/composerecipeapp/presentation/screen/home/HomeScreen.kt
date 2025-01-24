package com.surivalcoding.composerecipeapp.presentation.screen.home

import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil3.compose.AsyncImage
import com.surivalcoding.composerecipeapp.presentation.component.SearchComboBox
import com.surivalcoding.composerecipeapp.presentation.component.TabContainer
import com.surivalcoding.composerecipeapp.presentation.shared.AppTextStyles
import com.surivalcoding.composerecipeapp.presentation.shared.theme.AppColors
import kotlinx.serialization.Serializable

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    bottomPadding: Dp = 0.dp,
) {
    val scrollState = rememberScrollState()
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(scrollState),
        ) {
            HeadingSection(
                modifier = Modifier
                    .padding(horizontal = 30.dp, vertical = 20.dp),
                "Hello Jega", "What are you cooking today?"
            )
            SearchComboBox(
                modifier = Modifier.padding(horizontal = 30.dp),
                placeholderText = "Search recipe"
            )
            FeaturedSection(
                modifier = Modifier
                    .padding(vertical = 15.dp)
            )
            Spacer(modifier = Modifier.size(bottomPadding))
        }
    }
}

@Composable
fun FeaturedSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TabContainer(
            modifier = Modifier
                .padding(vertical = 10.dp),
            scrollPadding = PaddingValues(horizontal = 30.dp),
            labels = listOf("All", "Indian", "Chinese", "Italian", "Japanese", "Mexican"),
        )
        LazyRow() {
            items(items = (1..5).toList(), key = null) {
                FeaturedRecipeItem()
            }
        }
    }
}

@Composable
fun FeaturedRecipeItem(modifier: Modifier = Modifier) {
    Box {
        AsyncImage(
            contentDescription = null,
            model = ColorDrawable(Color.Yellow.toArgb()),
            modifier = Modifier.size(110.dp)
        )

    }
}

@Composable
private fun ColumnScope.HeadingSection(
    modifier: Modifier = Modifier,
    greeting: String,
    text: String,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(text = greeting, style = AppTextStyles.largeTextBold)
            Text(text = text, style = AppTextStyles.smallerTextRegular, color = AppColors.gray3)
        }
        FilledIconButton(
            onClick = {},
            shape = RoundedCornerShape(10.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = AppColors.secondary40,
                contentColor = Color.Transparent,
            ),
            modifier = Modifier.size(40.dp)
        ) {
            AsyncImage(
                contentDescription = null,
                model = "https://s3-alpha-sig.figma.com/img/c492/e0dc/4e79c0324e16a6e05cb4555a0dd25e28?Expires=1739145600&Key-Pair-Id=APKAQ4GOSFWCW27IBOMQ&Signature=ORZxIkBXfOgfdlsO4MIbxE8RXPyaJRDSqXaIqw4w2~xMid6zAQtaS4hTFjgLFkoFKTa2TkfBRU8CwfAHQ3uSvsLQpKFKfSxojkd~q5Ih4BarE2rqOqyTwLYoNsJKyTI64JNbVGI~8VRpiu5K0yE4sauC0qiLmeDQ6pujHwUcfWGSpxZr9n5yznG4~VPeeihpN0qVYlluAiQjW0sa9kNL6maZK3mWOVab5vH9okeAZawdwC33b30ZDeC9SV1PfRWHw20yxV0v6SOwNrAv6PEKJbi4KhfQc5OEWu3m5C3IhIl4eGUo2FGV7C2wEtwExqW5vEkVoZ6iJ7OKJjjooKubIA__"
            )
        }
    }
}

@Serializable
data object HomeRoute

fun NavGraphBuilder.homeScreen(bottomPadding: Dp) {
    composable<HomeRoute> {
        HomeScreen(bottomPadding = bottomPadding)
    }
}

@Preview
@Composable
private fun HomePreview() {
    HomeScreen()
}