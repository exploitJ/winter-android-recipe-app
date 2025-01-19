package com.surivalcoding.composerecipeapp.presentation.component

import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.Uri
import coil3.annotation.ExperimentalCoilApi
import coil3.asImage
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.toUri
import com.surivalcoding.composerecipeapp.ui.AppTextStyles
import com.surivalcoding.composerecipeapp.ui.theme.AppColors

@Composable
fun RecipeCard(
    modifier: Modifier = Modifier,
    title: String,
    author: String,
    rating: Float,
    image: Uri,
    cookingTimeInMinutes: Int,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(315f / 150f),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart,
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(modifier = Modifier.align(Alignment.Bottom)) {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.6f),
                        text = title,
                        style = AppTextStyles.smallTextBold.copy(color = Color.White),
                    )
                    Text(
                        text = "By $author",
                        style = AppTextStyles.smallerTextSmallLabel.copy(color = AppColors.gray4)
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                            .background(
                                shape = RoundedCornerShape(20.dp),
                                color = AppColors.secondary20
                            )
                            .padding(horizontal = 7.dp, vertical = 2.dp)
                            .align(Alignment.End),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = AppColors.rating,
                            modifier = Modifier.size(8.dp)
                        )
                        Text(
                            text = rating.toString(),
                            style = AppTextStyles.smallerTextSmallLabel,
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            Icons.Outlined.Timer,
                            contentDescription = null,
                            tint = AppColors.gray4,
                            modifier = Modifier.size(17.dp)
                        )
                        Text(
                            text = "$cookingTimeInMinutes min",
                            style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray4)
                        )
                        IconToggleButton(
                            checked = false, onCheckedChange = {},
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .background(shape = CircleShape, color = Color.White)
                                .size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.BookmarkBorder,
                                contentDescription = null,
                                tint = AppColors.primary80,
                                modifier = Modifier.size(16.dp),
                            )
                        }
                    }
                }
            }
            AsyncImage(
                modifier = Modifier.zIndex(-1f),
                model = image,
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
private fun RecipeCardPreview() {
    val previewHandler = AsyncImagePreviewHandler {
        ColorDrawable(Color.Blue.toArgb()).asImage()
    }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        RecipeCard(
            title = "Traditional spare ribs baked",
            image = "example.com".toUri(),
            cookingTimeInMinutes = 20,
            author = "Chef John",
            rating = 4.0f,
        )
    }
}