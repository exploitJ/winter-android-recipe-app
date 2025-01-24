package com.surivalcoding.composerecipeapp.presentation.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.surivalcoding.composerecipeapp.presentation.shared.AppTextStyles
import com.surivalcoding.composerecipeapp.presentation.shared.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComboBox(
    modifier: Modifier = Modifier,
    placeholderText: String,
    value: String = "",
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        BasicTextField(
            value = value,
            modifier = Modifier.weight(1f),
            onValueChange = {},
            enabled = true,
            textStyle = AppTextStyles.smallerTextRegular,
            singleLine = true,
            interactionSource = interactionSource,
            decorationBox =
            @Composable { innerTextField ->
                OutlinedTextFieldDefaults.DecorationBox(
                    contentPadding = PaddingValues(1.dp),
                    innerTextField = innerTextField,
                    placeholder = @Composable {
                        Text(
                            text = placeholderText,
                            style = AppTextStyles.smallerTextRegular
                        )
                    },
                    leadingIcon = @Composable {
                        Icon(
                            Icons.Outlined.Search,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = AppColors.gray4
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedPlaceholderColor = AppColors.gray4,
                        unfocusedPlaceholderColor = AppColors.gray4,
                    ),
                    container = {
                        OutlinedTextFieldDefaults.Container(
                            focusedBorderThickness = (1.5).dp,
                            unfocusedBorderThickness = (1.5).dp,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = AppColors.gray4,
                                unfocusedBorderColor = AppColors.gray4,
                            ),
                            shape = RoundedCornerShape(10.dp),
                            enabled = true,
                            isError = false,
                            interactionSource = interactionSource,
                        )
                    },
                    value = value,
                    enabled = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                )
            }
        )
        CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
            FilledTonalIconButton(
                onClick = {},
                shape = RoundedCornerShape(10.dp),
                colors = IconButtonDefaults.filledTonalIconButtonColors(
                    containerColor = AppColors.primary100,
                    contentColor = Color.White,
                ),
            ) {
                Icon(Icons.Filled.Tune, contentDescription = null)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SearchComboBoxPreview() {
    SearchComboBox(placeholderText = "Search recipe")
}