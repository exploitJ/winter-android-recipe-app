package com.surivalcoding.composerecipeapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.surivalcoding.composerecipeapp.presentation.shared.theme.AppColors

@Composable
fun BookmarkToggleButton(
    padding: PaddingValues = PaddingValues(start = 5.dp),
    onToggle: (Boolean) -> Unit,
) {
    IconToggleButton(
        checked = false,
        onCheckedChange = onToggle,
        modifier = Modifier
            .padding(paddingValues = padding)
            .background(shape = CircleShape, color = Color.White)
            .size(24.dp),
    ) {
        Icon(
            imageVector = Icons.Rounded.BookmarkBorder,
            contentDescription = null,
            tint = AppColors.primary80,
            modifier = Modifier.size(16.dp),
        )
    }
}
