package com.surivalcoding.composerecipeapp.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButtonColors
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.surivalcoding.composerecipeapp.ui.AppTextStyles
import com.surivalcoding.composerecipeapp.ui.theme.AppColors

@Composable
fun RatingButton(
    text: String,
    isSelected: Boolean,
) {
    OutlinedIconToggleButton(
        modifier = Modifier
            .width(47.dp)
            .height(28.dp),
        shape = RoundedCornerShape(10.dp),
        border = if (!isSelected) BorderStroke(1.dp, AppColors.primary100) else null,
        checked = isSelected,
        colors = IconToggleButtonColors(
            containerColor = if (isSelected) AppColors.primary100 else Color.White,
            contentColor = if (isSelected) Color.White else AppColors.primary100,
            disabledContainerColor = if (isSelected) AppColors.primary100 else Color.White,
            disabledContentColor = if (isSelected) Color.White else AppColors.primary100,
            checkedContainerColor = if (isSelected) AppColors.primary100 else Color.White,
            checkedContentColor = if (isSelected) Color.White else AppColors.primary100,
        ),

        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
            ) {
                Text(
                    text = text,
                    style = AppTextStyles.smallerTextSemiBold
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        },
        onCheckedChange = {}
    )
}

@Preview
@Composable
private fun RatingButtonPreview() {
    Row {
        RatingButton("3", true)
        RatingButton("4", false)
    }
}

