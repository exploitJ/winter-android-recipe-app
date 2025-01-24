package com.surivalcoding.composerecipeapp.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clipScrollableContainer
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.surivalcoding.composerecipeapp.presentation.shared.AppTextStyles
import com.surivalcoding.composerecipeapp.presentation.shared.theme.AppColors

@Composable
fun TabContainer(
    modifier: Modifier = Modifier,
    scrollPadding: PaddingValues = PaddingValues(0.dp),
    labels: List<String>,
    selected: Int = 0,
) {
    val scrollState = rememberScrollState()
    var selectedIndex by remember { mutableIntStateOf(selected) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RectangleShape)
            .horizontalScroll(scrollState)
            .padding(paddingValues = scrollPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
            labels.forEachIndexed { index, it ->
                FilterChip(
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                    },
                    label = {
                        Text(
                            text = it,
                            style = AppTextStyles.smallTextBold,
                            modifier = Modifier.padding(vertical = 7.dp, horizontal = 9.dp),
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = AppColors.primary100,
                        selectedLabelColor = Color.White,
                        labelColor = AppColors.primary100,
                        containerColor = Color.White,
                    ),
                    border = null,
                )
            }
        }
    }
}

@Preview
@Composable
private fun TabContainerPreview() {
    TabContainer(
        labels = listOf(
            "Label1",
            "Label2",
            "Label2",
            "Label2",
            "Label2",
            "Label2",
            "Label2",
            "Label2",
            "Label3"
        ),
    )
}

@Preview
@Composable
private fun TabPreview2Segments() {
    TabContainer(
        labels = listOf("Label1", "Label2"),
    )
}

@Composable
fun AppSegmentedButtonRow(
    modifier: Modifier = Modifier,
    scrollPadding: PaddingValues = PaddingValues(0.dp),
    labels: List<String>,
    onValueChange: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()
    var selectedIndex by remember { mutableIntStateOf(0) }

    SingleChoiceSegmentedButtonRow(
        modifier = modifier
            .clipScrollableContainer(Orientation.Horizontal)
            .clipToBounds()
            .horizontalScroll(scrollState)
            .padding(scrollPadding),
        space = (-11).dp
    ) {
        CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
            labels.forEachIndexed { index, it ->
                SegmentedButton(
                    modifier = Modifier.weight(
                        weight = 1f,
                        fill = false,
                    ),
                    selected = index == selectedIndex,
                    onClick = {
                        selectedIndex = index
                        onValueChange(index)
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = SegmentedButtonDefaults.colors(
                        activeContainerColor = AppColors.primary100,
                        activeContentColor = Color.White,
                        activeBorderColor = Color.Transparent,
                        inactiveContainerColor = Color.White,
                        inactiveContentColor = AppColors.primary100,
                        inactiveBorderColor = Color.Transparent,
                    ),
                    icon = {},
                    border = BorderStroke(0.dp, Color.Transparent),

                    ) {
                    Text(
                        text = it,
                        style = AppTextStyles.smallTextBold,
                    )
                }
            }
        }
    }

}