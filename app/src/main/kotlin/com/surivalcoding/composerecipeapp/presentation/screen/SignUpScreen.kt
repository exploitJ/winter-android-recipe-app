package com.surivalcoding.composerecipeapp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.platform.ViewConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.surivalcoding.composerecipeapp.presentation.component.BigButton
import com.surivalcoding.composerecipeapp.presentation.component.TextInput
import com.surivalcoding.composerecipeapp.ui.AppTextStyles
import com.surivalcoding.composerecipeapp.ui.theme.AppColors

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
) {
    val viewConfiguration = object : ViewConfiguration by LocalViewConfiguration.current {
        override val minimumTouchTargetSize: DpSize
            get() = DpSize(4.dp, 4.dp)

    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "Create an account",
                style = AppTextStyles.largeTextBold
            )
            Text(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(0.55f),
                text = "Let's help you set up your account, it won't take long.",
                overflow = TextOverflow.Ellipsis,
                minLines = 2,
                softWrap = true,
                style = AppTextStyles.smallerTextRegular
            )
        }

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        ) {
            TextInput(
                label = "Name",
                placeholder = "Enter Email",
                keyboardType = KeyboardType.Text,
            )
            TextInput(
                label = "Email",
                placeholder = "Enter Email",
                keyboardType = KeyboardType.Email,
            )
            TextInput(
                label = "Password",
                placeholder = "Enter Password",
                keyboardType = KeyboardType.Password,
            )
            TextInput(
                label = "Confirm Password",
                placeholder = "Enter Password",
                keyboardType = KeyboardType.Password,
            )

            Row(
                modifier = Modifier.align(Alignment.Start)
            ) {
                CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
                    Checkbox(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .size(17.dp)
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(5.dp),
                                color = AppColors.secondary100,
                            )
                            .align(Alignment.CenterVertically),
                        checked = false, onCheckedChange = {},
                        colors = CheckboxDefaults.colors().copy(
                            checkedBorderColor = Color.Transparent,
                            uncheckedBorderColor = Color.Transparent,
                        ),
                    )
                }
                TextButton(
                    onClick = {},
                    colors = ButtonDefaults.textButtonColors().copy(
                        contentColor = AppColors.secondary100
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Accept terms & Condition",
                        style = AppTextStyles.smallerTextRegular,
                    )
                }
            }
            BigButton(text = "Sign Up", onClick = {})

            ThirdPartySignInDivider()
            ThirdPartySignInCombo()
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Already a member?", style = AppTextStyles.smallerTextRegular)
            TextButton(
                onClick = {},
                colors = ButtonDefaults.textButtonColors().copy(
                    contentColor = AppColors.secondary100
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "Sign In",
                    style = AppTextStyles.smallerTextSemiBold
                )
            }
        }
    }
}


@Preview(device = Devices.PIXEL_7_PRO)
@Composable
private fun SignUnScreenPreview() {
    SignUpScreen()
}
