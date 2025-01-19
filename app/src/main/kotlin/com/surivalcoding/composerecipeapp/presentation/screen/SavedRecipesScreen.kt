package com.surivalcoding.composerecipeapp.presentation.screen

import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.Uri
import coil3.annotation.ExperimentalCoilApi
import coil3.asImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.surivalcoding.composerecipeapp.data.model.Email
import com.surivalcoding.composerecipeapp.data.model.Media
import com.surivalcoding.composerecipeapp.data.model.Post
import com.surivalcoding.composerecipeapp.data.model.PostId
import com.surivalcoding.composerecipeapp.data.model.Recipe
import com.surivalcoding.composerecipeapp.data.model.User
import com.surivalcoding.composerecipeapp.data.model.UserId
import com.surivalcoding.composerecipeapp.presentation.component.RecipeCard
import com.surivalcoding.composerecipeapp.ui.AppTextStyles
import com.surivalcoding.composerecipeapp.ui.theme.AppColors
import io.ktor.http.Url
import kotlinx.datetime.Clock
import java.util.UUID

@Composable
fun SavedRecipesScreen(
    modifier: Modifier = Modifier,
    recipes: List<Post<Recipe>>,
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Saved Recipes",
            style = AppTextStyles.mediumTextBold.copy(color = AppColors.font),
            modifier = Modifier.padding(10.dp)
        )
        LazyColumn(
            modifier = modifier.fillMaxHeight(),
            contentPadding = PaddingValues(horizontal = 30.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(
                items = recipes,
                itemContent = {
                    RecipeCard(
                        modifier = Modifier,
                        title = it.title,
                        rating = it.content.starRating,
                        image = it.thumbnail?.url ?: Uri(),
                        cookingTimeInMinutes = it.content.cookingTimeInMinutes,
                        author = it.author.nickname,
                    )
                })

        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
private fun SavedRecipesScreenPreview() {
    val user = User(
        id = UserId(UUID.randomUUID()),
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
        id = PostId(UUID.randomUUID()),
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
        ColorDrawable(Color.DarkGray.toArgb()).asImage()
    }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        SavedRecipesScreen(recipes = recipes)
    }
}