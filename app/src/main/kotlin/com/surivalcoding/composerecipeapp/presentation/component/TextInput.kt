package com.surivalcoding.composerecipeapp.presentation.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.surivalcoding.composerecipeapp.ui.AppTextStyles
import com.surivalcoding.composerecipeapp.ui.theme.AppColors
import kotlin.String


@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    label: String,
    defaultValue: String? = null,
    placeholder: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true,
) {
    val textState = remember { mutableStateOf(defaultValue ?: "") }
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier.wrapContentSize()
    ) {
        Text(text = label, style = AppTextStyles.smallTextRegular)
        OutlinedTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            textStyle = AppTextStyles.smallTextRegular,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedPlaceholderColor = AppColors.gray4,
                focusedBorderColor = AppColors.primary80,
                unfocusedContainerColor = Color.White,
                unfocusedPlaceholderColor = AppColors.gray4,
                unfocusedBorderColor = AppColors.gray4,
                disabledContainerColor = AppColors.gray4,
                disabledPlaceholderColor = AppColors.gray2,
                disabledBorderColor = AppColors.gray4,
                cursorColor = AppColors.gray1,
            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            interactionSource = interactionSource,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            enabled = enabled,
            placeholder = {
                placeholder?.let {
                    Text(
                        text = it,
                        style = AppTextStyles.smallTextRegular
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xF0FF)
@Composable
private fun TextInputDefault() {
    TextInput(
        label = "Label",
        placeholder = "Placeholder",
    )
}

@Preview(showBackground = true, backgroundColor = 0xF0FF)
@Composable
private fun TextInputFilled() {
    TextInput(
        label = "Label",
        defaultValue = "default value",
    )
}

@Preview(showBackground = true)
@Composable
private fun TextInputDisabled() {
    TextInput(
        label = "Label",
        placeholder = "Placeholder",
        enabled = false
    )
}
