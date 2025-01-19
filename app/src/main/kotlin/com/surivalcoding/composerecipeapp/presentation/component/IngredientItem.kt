package com.surivalcoding.composerecipeapp.presentation.component

import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.annotation.ExperimentalCoilApi
import coil3.asImage
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.toUri
import com.surivalcoding.composerecipeapp.data.model.Ingredient
import com.surivalcoding.composerecipeapp.data.model.Media
import com.surivalcoding.composerecipeapp.ui.AppTextStyles
import com.surivalcoding.composerecipeapp.ui.theme.AppColors
import io.ktor.http.Url

@Composable
fun IngredientItem(
    ingredient: Ingredient,
) {
    Row(
        modifier = Modifier
            .background(color = AppColors.gray4, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 15.dp, vertical = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .padding(6.dp)
                .wrapContentSize()
        ) {
            AsyncImage(
                model = ingredient.thumbnail.url,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = ingredient.name,
            style = AppTextStyles.normalTextBold.copy(color = AppColors.font)
        )
        Text(text = "${ingredient.amountInGrams}g",
            style = AppTextStyles.smallTextRegular.copy(color = AppColors.gray3))
    }

}

@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
private fun IngredientItemShowcase() {
    val previewHandler = AsyncImagePreviewHandler {
        ColorDrawable(Color.Blue.toArgb()).asImage()
    }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        IngredientItem(ingredient = Ingredient(
            name = "Tomatoes",
            shortName = "Tomatoes",
            thumbnail = Media.Image("https://example.com/tomatoes.jpg".toUri()),
            amountInGrams = 500,
        ))
    }
}