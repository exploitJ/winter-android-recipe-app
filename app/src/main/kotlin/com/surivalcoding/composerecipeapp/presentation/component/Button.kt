package com.surivalcoding.composerecipeapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.surivalcoding.composerecipeapp.presentation.shared.AppTextStyles
import com.surivalcoding.composerecipeapp.presentation.shared.theme.AppColors
import kotlin.String

@Composable
private fun Button(
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit = {},
    icon: @Composable () -> Unit = {},
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val buttonEnableState = rememberSaveable { mutableStateOf(enabled) }

    Box(
        modifier = modifier
            .background(
                color = if (buttonEnableState.value) AppColors.primary100 else AppColors.gray4,
                shape = RoundedCornerShape(10.dp),
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        buttonEnableState.value = false
                        awaitRelease()
                        onClick()
                        buttonEnableState.value = true
                    },
                )
            },
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            text()
            icon()
        }
    }
}

@Composable
fun BigButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        text = {
            Text(
                text = text,
                style = AppTextStyles.normalTextBold.copy(
                    color = Color.White,
                ),
            )
        },
        icon = {
            Spacer(modifier = Modifier.width(11.dp))
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = null,
                tint = Color.White,
            )
        },
        onClick = {
            onClick()
        }
    )
}

@Composable
fun MediumButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier
            .width(243.dp)
            .height(54.dp),
        text = {
            Text(
                text = text,
                style = AppTextStyles.normalTextBold.copy(
                    color = Color.White,
                ),
            )
        },
        icon = {
            Spacer(modifier = Modifier.width(9.dp))
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = null,
                tint = Color.White,
            )
        },
        onClick = {
            onClick()
        }
    )
}

@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier
            .width(174.dp)
            .height(37.dp),
        text = {
            Text(
                text = text,
                style = AppTextStyles.smallTextBold.copy(
                    color = Color.White,
                ),
            )
        },
        onClick = {
            onClick()
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun BigButtonPreview() {
    BigButton(
        text = "Button"
    )
}

@Preview(showBackground = true)
@Composable
private fun MediumButtonPreview() {
    MediumButton(
        text = "Button"
    )
}

@Preview(showBackground = true)
@Composable
private fun SmallButtonPreview() {
    SmallButton(
        text = "Button"
    )
}
