package com.surivalcoding.composerecipeapp.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastRoundToInt
import androidx.core.graphics.drawable.toDrawable
import coil3.annotation.ExperimentalCoilApi
import coil3.asImage
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.toUri
import com.surivalcoding.composerecipeapp.domain.model.Media
import com.surivalcoding.composerecipeapp.domain.model.Post
import com.surivalcoding.composerecipeapp.domain.model.Recipe
import com.surivalcoding.composerecipeapp.presentation.component.BookmarkToggleButton
import com.surivalcoding.composerecipeapp.presentation.component.SearchComboBox
import com.surivalcoding.composerecipeapp.presentation.component.TabContainer
import com.surivalcoding.composerecipeapp.presentation.shared.AppTextStyles
import com.surivalcoding.composerecipeapp.presentation.shared.PreviewData
import com.surivalcoding.composerecipeapp.presentation.shared.boxShadow
import com.surivalcoding.composerecipeapp.presentation.shared.theme.AppColors
import java.util.UUID

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    bottomPadding: Dp = 0.dp,
    userUiState: UserUiState,
    newRecipeUiState: NewRecipeUiState,
    featuredRecipeUiState: FeaturedRecipeUiState,
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
            when (userUiState) {
                UserUiState.Anonymous -> {
                    HeadingSection(
                        modifier = Modifier
                            .padding(horizontal = 30.dp, vertical = 20.dp),
                        profileImage = Media.Image(url = "".toUri()),
                        greeting = "Hello Guest", text = "What are you cooking today?"
                    )
                }

                is UserUiState.Success -> {
                    val userName = userUiState.user.nickname
                    HeadingSection(
                        modifier = Modifier
                            .padding(horizontal = 30.dp, vertical = 20.dp),
                        profileImage = userUiState.user.profileImage,
                        "Hello $userName", "What are you cooking today?"
                    )

                }
            }
            SearchComboBox(
                modifier = Modifier.padding(horizontal = 30.dp),
                placeholderText = "Search recipe"
            )
            when (featuredRecipeUiState) {
                is FeaturedRecipeUiState.Success ->
                    FeaturedSection(
                        modifier = Modifier
                            .padding(vertical = 15.dp),
                        filterTags = listOf(
                            "All",
                            "Indian",
                            "Chinese",
                            "Italian",
                            "Japanese",
                            "Mexican"
                        ),
                        recipeCards = featuredRecipeUiState.recipes.toList(),
                    )

                FeaturedRecipeUiState.Empty,
                FeaturedRecipeUiState.Loading,
                    ->
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(text = "Empty")
                    }
            }
            when (newRecipeUiState) {
                is NewRecipeUiState.Success -> {
                    NewRecipeSection(
                        modifier = Modifier.padding(bottom = 20.dp),
                        recipes = newRecipeUiState.recipes
                    )
                }

                NewRecipeUiState.Loading -> {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(text = "Empty")
                    }
                }
            }
            Spacer(modifier = Modifier.size(bottomPadding))
        }
    }
}

@Composable
fun NewRecipeSection(
    modifier: Modifier = Modifier,
    sectionTitle: String = "New Recipe",
    recipes: List<Post<Recipe>> = emptyList(),
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            style = AppTextStyles.normalTextBold,
            text = sectionTitle,
            modifier = Modifier.padding(start = 30.dp, end = 30.dp, bottom = 5.dp)
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(recipes) { item ->
                ShortRecipeCard(
                    cookingTimeInMinutes = item.content.cookingTimeInMinutes,
                    image = @Composable { radius ->
                        val model = item.thumbnail?.url
                        val sharedModifier = Modifier
                            .size(radius * 2)
                            .clip(shape = CircleShape)
                        if (model == null) {
                            Icon(
                                imageVector = Icons.Default.Image,
                                contentDescription = null,
                                modifier = sharedModifier,
                            )
                        } else {
                            AsyncImage(
                                contentDescription = null,
                                model = model,
                                modifier = sharedModifier,
                                contentScale = ContentScale.Crop,
                            )
                        }
                    },
                    onClick = {},
                    imgRadius = 43.dp,
                    starCount = item.content.starRating.fastRoundToInt()
                )
            }
        }
    }

}

@Composable
fun ShortRecipeCard(
    modifier: Modifier = Modifier,
    cookingTimeInMinutes: Int,
    image: @Composable (Dp) -> Unit,
    onClick: () -> Unit,
    imgRadius: Dp = 43.dp,
    starCount: Int,
) {
    Box(
        modifier = modifier
            .clickable {
                onClick()
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .boxShadow(
                    color = Color.Black.copy(alpha = 0.12f),
                    blurRadius = 8.dp,
                    shape = RoundedCornerShape(10.dp)
                )
                .background(shape = RoundedCornerShape(10.dp), color = Color.White)
                .padding(10.dp)
                .align(Alignment.BottomStart),

            ) {

            Column(

                modifier = Modifier.padding(end = 14.dp),
            ) {
                Text(
                    text = "asdfasd",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = AppTextStyles.smallTextBold,
                    color = AppColors.gray1,
                    modifier = Modifier.widthIn(100.dp, 150.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 5.dp, bottom = 10.dp)
                ) {
                    repeat(starCount) {
                        Icon(
                            imageVector = Icons.Filled.Star, contentDescription = null,
                            modifier = modifier.size(12.dp), tint = AppColors.rating
                        )
                    }
                    repeat(5 - starCount) {
                        Icon(
                            imageVector = Icons.Outlined.Star, contentDescription = null,
                            modifier = modifier.size(12.dp), tint = AppColors.rating
                        )
                    }

                }
                Text(text = "asdfasd")
            }
            Spacer(Modifier.width(imgRadius * 2))
        }

        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .padding(end = 10.dp, bottom = 10.dp)
                .align(Alignment.BottomEnd),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            image(imgRadius)
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    Icons.Outlined.Timer,
                    contentDescription = null,
                    tint = AppColors.gray3,
                    modifier = Modifier.size(17.dp)
                )
                Text(
                    text = "$cookingTimeInMinutes mins",
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.gray3,
                    softWrap = false,
                )
            }

        }
    }
}


@Composable
fun FeaturedSection(
    modifier: Modifier = Modifier,
    filterTags: List<String>,
    recipeCards: List<Post<Recipe>>,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TabContainer(
            modifier = Modifier
                .padding(vertical = 10.dp),
            scrollPadding = PaddingValues(horizontal = 30.dp),
            labels = filterTags,
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(top = 15.dp, bottom = 20.dp, start = 30.dp, end = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(
                items = recipeCards,
                key = {
                    it.id
                }) { item ->
                CompactRecipeCard(
                    image = @Composable { radius ->
                        val model = item.thumbnail?.url
                        val sharedModifier = Modifier.size(radius * 2)
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .clip(shape = CircleShape),
                        ) {
                            if (model == null) {
                                Icon(
                                    imageVector = Icons.Default.Image,
                                    contentDescription = null,
                                    modifier = sharedModifier,
                                )
                            } else {
                                AsyncImage(
                                    contentDescription = null,
                                    model = model,
                                    modifier = sharedModifier,
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        }
                    },
                    onClick = {},
                    onBookmarkToggle = {},
                    imgRadius = 55.dp,
                    padding = 20.dp,
                    title = "Classic Greek Salad",
                )
            }
        }
    }
}

@Composable
fun LazyItemScope.CompactRecipeCard(
    modifier: Modifier = Modifier,
    image: @Composable (Dp) -> Unit,
    onClick: () -> Unit,
    onBookmarkToggle: (Boolean) -> Unit,
    imgRadius: Dp,
    padding: Dp,
    title: String,
) {
    Box(
        modifier = modifier
            .width((imgRadius + padding) * 2)
            .clickable {
                onClick()
            },
    ) {
        Box(
            modifier = Modifier
                .padding(top = imgRadius)
                .matchParentSize()
                .background(
                    color = AppColors.gray4,
                    shape = RoundedCornerShape(12.dp),
                )
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            image(imgRadius)
            Text(
                modifier = Modifier
                    .padding(top = 11.dp, bottom = 19.dp)
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                text = title,
                minLines = 2, maxLines = 2,
                style = AppTextStyles.smallTextBold,
                textAlign = TextAlign.Center,
                color = AppColors.gray1,
                overflow = TextOverflow.Ellipsis,
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Time",
                        style = AppTextStyles.smallerTextRegular,
                        color = AppColors.gray3,
                    )
                    Text(
                        text = "15 mins",
                        style = AppTextStyles.smallerTextBold,
                        color = AppColors.gray1,
                    )
                }

                BookmarkToggleButton(onToggle = onBookmarkToggle)
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 30.dp)
                .background(
                    color = AppColors.secondary20,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 7.dp)
                .align(Alignment.TopEnd),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = (6.5).dp)
                    .size(10.dp),
                tint = AppColors.rating,
            )
            Text(
                text = "4.5",
                style = AppTextStyles.smallerTextRegular,
            )

        }
    }

}

@Composable
private fun ColumnScope.HeadingSection(
    modifier: Modifier = Modifier,
    profileImage: Media.Image,
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
                model = profileImage.url,
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
private fun HomePreview() {

    val previewHandler = AsyncImagePreviewHandler {
        Color.Green.toArgb().toDrawable().asImage()
    }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        HomeScreen(
            userUiState = UserUiState.Anonymous,
            newRecipeUiState = NewRecipeUiState.Success(
                recipes = listOf(
                    PreviewData.postRecipe.copy(id = UUID.randomUUID()),
                    PreviewData.postRecipe.copy(id = UUID.randomUUID()),
                    PreviewData.postRecipe.copy(id = UUID.randomUUID()),
                )
            ),
            featuredRecipeUiState = FeaturedRecipeUiState.Success(
                recipes = setOf(
                    PreviewData.postRecipe,
                    PreviewData.postRecipe.copy(id = UUID.randomUUID()),
                    PreviewData.postRecipe.copy(id = UUID.randomUUID()),
                    PreviewData.postRecipe.copy(id = UUID.randomUUID()),
                )
            )
        )
    }
}