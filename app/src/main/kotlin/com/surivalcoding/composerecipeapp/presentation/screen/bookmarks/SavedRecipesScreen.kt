package com.surivalcoding.composerecipeapp.presentation.screen.bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toDrawable
import coil3.Uri
import coil3.annotation.ExperimentalCoilApi
import coil3.asImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.surivalcoding.composerecipeapp.domain.model.Email
import com.surivalcoding.composerecipeapp.domain.model.Media
import com.surivalcoding.composerecipeapp.domain.model.Post
import com.surivalcoding.composerecipeapp.domain.model.Recipe
import com.surivalcoding.composerecipeapp.domain.model.User
import com.surivalcoding.composerecipeapp.presentation.component.RecipeCard
import com.surivalcoding.composerecipeapp.presentation.shared.AppTextStyles
import com.surivalcoding.composerecipeapp.presentation.shared.Loading
import com.surivalcoding.composerecipeapp.presentation.shared.theme.AppColors
import io.ktor.http.Url
import kotlinx.datetime.Clock
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SavedRecipesScreen(
    modifier: Modifier = Modifier,
    state: SavedRecipesState,
    bottomBarPadding: Dp = 0.dp,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                expandedHeight = 47.dp,
                title = {
                    Text(
                        text = "Saved Recipes",
                        style = AppTextStyles.mediumTextBold,
                        color = AppColors.font,
                    )
                },
            )
        }
    ) { innerPadding ->
        if (state.isLoading) {
            Loading()
        } else {
            LazyColumn(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxHeight(),
                contentPadding = PaddingValues(
                    top = 10.dp,
                    bottom = bottomBarPadding,
                    start = 30.dp,
                    end = 30.dp
                ),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(
                    items = state.savedRecipes,
                    itemContent = {
                        RecipeCard(
                            modifier = Modifier,
                            title = it.title,
                            rating = it.content.starRating,
                            image = it.thumbnail?.url ?: Uri(),
                            cookingTimeInMinutes = it.content.cookingTimeInMinutes,
                            author = it.author.nickname,
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
private fun SavedRecipesScreenPreview() {
    val user = User(
        id = UUID.randomUUID(),
        memberSince = Clock.System.now(),
        nickname = "Chef Gordon",
        fullName = "",
        email = Email(""),
        profileImage = Media.Image(Uri("")),
        following = emptySet(),
        followers = emptySet(),
        bio = "",
        occupation = "",
        address = "",
        savedPosts = emptyList(),
    )
    val post = Post(
        id = UUID.randomUUID(),
        author = user,
        title = "Traditional spare ribs baked",
        comments = emptyList(),
        shareableLink = Url(""),
        editedAt = Clock.System.now(),
        createdAt = Clock.System.now(),
        thumbnail = Media.Image(Uri("")),
        media = emptyList(),
        content = Recipe(
            ingredients = emptyList(),
            instruction = emptyList(),
            starRating = 4.0f,
            cookingTimeInMinutes = 20,
            servings = 2,
            tags = emptySet(),
        )
    )
    val recipes = (1..10).map {
        post
    }

    val previewHandler = AsyncImagePreviewHandler {
        Color.DarkGray.toArgb().toDrawable().asImage()
    }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        SavedRecipesScreen(state = SavedRecipesState(recipes, false))
    }
}
