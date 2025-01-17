package com.surivalcoding.composerecipeapp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.toUri
import com.surivalcoding.composerecipeapp.R
import com.surivalcoding.composerecipeapp.presentation.component.BigButton
import com.surivalcoding.composerecipeapp.presentation.component.TextInput
import com.surivalcoding.composerecipeapp.ui.AppTextStyles
import com.surivalcoding.composerecipeapp.ui.theme.AppColors

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Hello,",
                style = AppTextStyles.headerTextBold
            )
            Text(
                text = "Welcome Back!",
                style = AppTextStyles.largeTextRegular
            )
        }
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        ) {
            TextInput(
                label = "Email",
                placeholder = "Enter Email",
                keyboardType = KeyboardType.Email,
            )
            TextInput(
                modifier = Modifier.padding(top = 10.dp),
                label = "Enter Password",
                placeholder = "Enter Password",
                keyboardType = KeyboardType.Password,
            )

            TextButton(
                modifier = Modifier
                    .align(Alignment.Start),
                onClick = {},
                colors = ButtonDefaults.textButtonColors().copy(
                    contentColor = AppColors.secondary100
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "Forgot Password?",
                    style = AppTextStyles.smallerTextRegular,
                )
            }
            BigButton(text = "Sign In", onClick = {})

            ThirdPartySignInDivider()
            ThirdPartySignInCombo()

        }
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Don't have an account?", style = AppTextStyles.smallerTextRegular)
            TextButton(onClick = {},
                colors = ButtonDefaults.textButtonColors().copy(
                    contentColor = AppColors.secondary100
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "Sign Up",
                    style = AppTextStyles.smallerTextSemiBold
                )
            }
        }
    }
}

@Composable
fun ThirdPartySignInCombo() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterHorizontally),
    ) {
        SignInButton(onClick = {}) {
            SvgIcon(
                resourceId = R.raw.android_neutral_sq_na,
            )
        }
        SignInButton(onClick = {}) {
            AsyncImage(
                model = R.drawable.facebook_logo_primary,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun ThirdPartySignInDivider() {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        HorizontalDivider(
            modifier = Modifier.width(50.dp),
            thickness = 1.dp, color = AppColors.gray4
        )
        Text(
            text = "Or Sign in with",
            style = AppTextStyles.smallerTextSemiBold.copy(color = AppColors.gray4)
        )
        HorizontalDivider(
            modifier = Modifier.width(50.dp),
            thickness = 1.dp, color = AppColors.gray4
        )
    }
}

@Composable
fun SignInButton(onClick: () -> Unit, content: @Composable () -> Unit = {}) {
    ElevatedButton(
        modifier = Modifier.size(44.dp),
        onClick = { onClick() },
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Unspecified,
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp)
    ) {
        content()
    }
}

@Composable
fun SvgIcon(modifier: Modifier = Modifier, resourceId: Int) {
    val svgUri = "android.resource://${LocalContext.current.packageName}/$resourceId".toUri()
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(svgUri).build(),
        contentDescription = null,
        modifier = modifier,
    )
}


@Preview(device = Devices.PIXEL_7_PRO)
@Composable
private fun SignInScreenPreview() {
    SignInScreen()
}