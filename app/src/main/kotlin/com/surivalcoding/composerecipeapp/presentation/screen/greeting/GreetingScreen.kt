package com.surivalcoding.composerecipeapp.presentation.screen.greeting

import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.annotation.ExperimentalCoilApi
import coil3.asImage
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.surivalcoding.composerecipeapp.R
import com.surivalcoding.composerecipeapp.presentation.component.BigButton
import com.surivalcoding.composerecipeapp.presentation.shared.AppTextStyles
import kotlinx.serialization.Serializable

@Serializable
data object GreetingRoute

@Composable
fun GreetingScreen(
    selfPromote: String,
    emphasis: String,
    description: String,
    buttonText: String,
    onClick: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val resources = LocalContext.current.resources
        val bg = resources.getDrawable(R.drawable.bg_greeting, null)
        AsyncImage(
            model = bg,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Black.copy(alpha = 0.4f), Color.Black),
                    ),
                    alpha = 0.8f
                )
        )

        val chefHat = resources.getDrawable(R.drawable.img_chef_hat, null)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                AsyncImage(
                    modifier = Modifier.size(79.dp),
                    model = chefHat,
                    contentDescription = null,
                )
                Text(
                    text = selfPromote,
                    style = AppTextStyles.mediumTextBold,
                    color = Color.White,
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = emphasis,
                    style = AppTextStyles.titleTextSemibold,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = description,
                    style = AppTextStyles.normalTextRegular,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.size(64.dp))
                BigButton(text = buttonText, onClick = { onClick() })
            }
        }

    }

}

@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
private fun GreetingPreview() {
    val previewHandler = AsyncImagePreviewHandler {
        ColorDrawable(Color.Blue.toArgb()).asImage()
    }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        GreetingScreen(
            selfPromote = "100K+ Premium Recipe",
            emphasis = "Get Cooking",
            description = "Simple way to find Tasty Recipe",
            buttonText = "Start Cooking"
        )
    }
}
