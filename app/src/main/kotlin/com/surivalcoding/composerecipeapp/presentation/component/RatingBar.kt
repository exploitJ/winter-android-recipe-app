package com.surivalcoding.composerecipeapp.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RatingBar(
    rating: Float,
    maxRating: Int = 5,
) {
    var rating by remember { mutableFloatStateOf(rating) }

    Row { }
}

@Preview
@Composable
private fun RatingBarDialogPreview() {

}