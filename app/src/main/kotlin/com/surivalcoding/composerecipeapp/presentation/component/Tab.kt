package com.surivalcoding.composerecipeapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.surivalcoding.composerecipeapp.ui.AppTextStyles
import com.surivalcoding.composerecipeapp.ui.theme.AppColors

@Composable
fun TabContainer(
    modifier: Modifier = Modifier,
    labels: List<String>,
    selectedIndex: Int,
    onValueChange: (Int) -> Unit,
) {
    Row(
        modifier = modifier
            .background(color = Color.White, shape = RectangleShape)
            .width(375.dp)
            .height(58.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(20.dp))
        labels.forEachIndexed { index, it ->
            Segment(label = it, modifier = Modifier.weight(1f), isSelected = index == selectedIndex)
            onValueChange(index)
            Spacer(Modifier.width(8.dp))
        }
        Spacer(Modifier.width(12.dp))
    }
}

@Composable
fun Segment(modifier: Modifier = Modifier, label: String, isSelected: Boolean) {
    val color = if (isSelected) Color.White else AppColors.primary80
    val backgroundColor = if (isSelected) AppColors.primary100 else Color.Transparent

    Box(
        modifier = modifier.background(
            color = backgroundColor,
            shape = RoundedCornerShape(10.dp),
        ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label,
            style = AppTextStyles.smallTextBold.copy(color = color),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
private fun TabContainerPreview() {
    TabContainer(
        labels = listOf("Label1", "Label2", "Label3"),
        selectedIndex = 0,
        onValueChange = {},
    )
}

@Preview
@Composable
private fun TabPreview2Segments() {
    TabContainer(
        labels = listOf("Label1", "Label2"),
        selectedIndex = 1,
        onValueChange = {},
    )
}
