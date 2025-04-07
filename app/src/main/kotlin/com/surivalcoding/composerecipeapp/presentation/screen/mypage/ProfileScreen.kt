package com.surivalcoding.composerecipeapp.presentation.screen.mypage

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toDrawable
import coil3.annotation.ExperimentalCoilApi
import coil3.asImage
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.toUri
import com.surivalcoding.composerecipeapp.domain.model.Media
import com.surivalcoding.composerecipeapp.presentation.component.AppSegmentedButtonRow
import com.surivalcoding.composerecipeapp.presentation.component.RecipeCard
import com.surivalcoding.composerecipeapp.presentation.shared.AppTextStyles
import com.surivalcoding.composerecipeapp.presentation.shared.Loading
import com.surivalcoding.composerecipeapp.presentation.shared.PreviewData
import com.surivalcoding.composerecipeapp.presentation.shared.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    bottomPadding: Dp,
    myInfoUiState: ProfileUiState,
    savedPostsUiState: SavedPostsUiState,
) {
    var categoryIndex by rememberSaveable { mutableIntStateOf(0) }
    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            CenterAlignedTopAppBar(
                expandedHeight = 47.dp,
                title = {
                    Text(
                        text = "Profile",
                        style = AppTextStyles.mediumTextBold,
                        color = AppColors.font,
                    )
                },
                actions = {
                    IconButton(
                        onClick = { /* Handle menu click */ },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.Black,
                            containerColor = Color.Transparent
                        )
                    ) {
                        Icon(
                            Icons.Default.MoreHoriz,
                            contentDescription = null,
                        )
                    }
                }
            )
        }) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 30.dp),
        ) {
            when (myInfoUiState) {
                is ProfileUiState.Success -> {
                    Row(
                        modifier = Modifier,
                        Arrangement.spacedBy(25.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val model = myInfoUiState.myInfo.profileImage.url
                        val labels = arrayOf("Recipe", "Followers", "Following")
                        val values = myInfoUiState.myInfo.let {
                            arrayOf(it.savedPosts.size, it.followers.size, it.following.size)
                        }

                        AsyncImage(
                            contentDescription = null,
                            model = model,
                            modifier = Modifier
                                .size(99.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop,
                        )
                        labels.zip(values).forEach { pair ->
                            StatItem(label = pair.first, value = pair.second.toString())
                        }
                    }
                    Text(
                        text = myInfoUiState.myInfo.fullName,
                        style = AppTextStyles.normalTextBold,
                        color = AppColors.font,
                        modifier = Modifier.padding(top = 15.dp)
                    )
                    Text(
                        text = myInfoUiState.myInfo.occupation,
                        style = AppTextStyles.smallerTextRegular,
                        color = AppColors.gray3,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    ExpandableText(text = myInfoUiState.myInfo.bio)

                    val categories = listOf("Recipe", "Videos", "Tag")
                    AppSegmentedButtonRow(
                        labels = categories,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 25.dp)
                    ) { index ->
                        categoryIndex = index
                    }

                    when (savedPostsUiState) {
                        SavedPostsUiState.Loading -> {
                            Loading()
                        }

                        is SavedPostsUiState.Success -> {
                            LazyColumn(
                                modifier = Modifier,
                                verticalArrangement = Arrangement.spacedBy(20.dp),
                            ) {
                                val items =
                                    when (categoryIndex) {
                                        0 -> savedPostsUiState.recipes
                                        1 -> savedPostsUiState.recipes.filter {
                                            it.media.isNotEmpty() && it.media.filterIsInstance<Media.Video>()
                                                .isNotEmpty()
                                        }

                                        2 -> savedPostsUiState.recipes.filter {
                                            it.content.tags.isNotEmpty()
                                        }

                                        else -> savedPostsUiState.recipes
                                    }
                                itemsIndexed(items = items) { index, post ->
                                    RecipeCard(
                                        title = post.title,
                                        author = post.author.nickname,
                                        rating = post.content.starRating,
                                        image = post.thumbnail?.url ?: "".toUri(),
                                        cookingTimeInMinutes = post.content.cookingTimeInMinutes,
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.padding(bottom = bottomPadding))

                        }
                    }

                }

                ProfileUiState.Loading -> Loading()
            }
        }
    }
}


@Composable
fun StatItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = AppTextStyles.smallerTextRegular,
            color = AppColors.gray3,
        )
        Text(
            text = value,
            style = AppTextStyles.largeTextBold,
            color = AppColors.font,
        )
    }
}

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false,
    maxLines: Int = 2,
) {
    var isExpanded by remember { mutableStateOf(initiallyExpanded) }
    var isTextOverflowing by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text(
            text = text,
            maxLines = if (isExpanded) Int.MAX_VALUE else maxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResult ->
                isTextOverflowing = textLayoutResult.hasVisualOverflow
            },
            style = AppTextStyles.smallerTextRegular,
            color = AppColors.gray2,
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
        )

        if (isTextOverflowing || isExpanded) {
            CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
                TextButton(
                    onClick = { isExpanded = !isExpanded },
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Text(
                        text = if (isExpanded) "Show Less" else "More...",
                        style = AppTextStyles.smallerTextRegular, color = AppColors.primary80
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpandableTextPreview() {
    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            ExpandableText(
                text = "This is a long bio text that should be initially truncated to just two lines. " +
                        "When the user clicks on the 'Show More' button, the text will expand to show " +
                        "the entire content. This helps save space while allowing users to read more if they're interested. " +
                        "This is useful for user profiles, item descriptions, and other areas where space is limited but " +
                        "additional information might be valuable to some users.",
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
private fun ProfileScreenPreview() {
    val myInfoUiState = ProfileUiState.Success(PreviewData.user)

    val previewHandler = AsyncImagePreviewHandler {
        Color.Blue.toArgb().toDrawable().asImage()
    }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        ProfileScreen(
            bottomPadding = 20.dp,
            myInfoUiState = myInfoUiState,
            savedPostsUiState = SavedPostsUiState.Success((1..5).map { PreviewData.postRecipe }),
        )
    }
}