package com.surivalcoding.composerecipeapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.surivalcoding.composerecipeapp.presentation.screen.greeting.GreetingRoute
import com.surivalcoding.composerecipeapp.presentation.screen.greeting.GreetingScreen
import com.surivalcoding.composerecipeapp.presentation.screen.navigateToApp
import com.surivalcoding.composerecipeapp.presentation.screen.signin.SignInRoute
import com.surivalcoding.composerecipeapp.presentation.screen.signin.SignInScreen
import com.surivalcoding.composerecipeapp.presentation.screen.signin.returnToSignIn
import com.surivalcoding.composerecipeapp.presentation.screen.signup.navigateToSignUp
import com.surivalcoding.composerecipeapp.presentation.screen.signup.signUpScreen
import com.surivalcoding.composerecipeapp.presentation.screen.topLevelBase

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = GreetingRoute) {
        composable<GreetingRoute> {
            GreetingScreen(
                selfPromote = "100K+ Premium Recipe",
                emphasis = "Get Cooking",
                description = "Simple way to find Tasty Recipe",
                buttonText = "Start Cooking",
                onClick = {
                    navController.navigate(SignInRoute, navOptions = navOptions {
                        popUpTo<GreetingRoute> {
                            inclusive = true
                        }
                    })
                }
            )
        }

        composable<SignInRoute> {
            SignInScreen(
                onSubmit = navController::navigateToApp,
                onClickSignUp = navController::navigateToSignUp
            )
        }

        signUpScreen(
            onSubmit = navController::returnToSignIn,
            onClickReturn = navController::returnToSignIn
        )

        topLevelBase()
    }

}
