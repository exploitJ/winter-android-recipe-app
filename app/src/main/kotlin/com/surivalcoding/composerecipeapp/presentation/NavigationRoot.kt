package com.surivalcoding.composerecipeapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.surivalcoding.composerecipeapp.presentation.screen.SignInScreen
import com.surivalcoding.composerecipeapp.presentation.screen.SignUpScreen
import com.surivalcoding.composerecipeapp.presentation.screen.greeting.GreetingScreen
import com.surivalcoding.composerecipeapp.presentation.screen.mypage.SavedRecipesScreen
import com.surivalcoding.composerecipeapp.presentation.screen.mypage.SavedRecipesViewModel
import kotlinx.serialization.Serializable

@Serializable
data object App {

    @Serializable
    data object Home

    @Serializable
    data object SavedRecipes

    @Serializable
    data object Notifications

    @Serializable
    data object Profile
}

@Serializable
data object Greeting

@Serializable
data object SignIn

@Serializable
data object SignUp


@Composable
fun NavigationRoot() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Greeting) {
        composable<Greeting> {
            GreetingScreen(
                selfPromote = "100K+ Premium Recipe",
                emphasis = "Get Cooking",
                description = "Simple way to find Tasty Recipe",
                buttonText = "Start Cooking",
                onClick = {
                    navController.navigate(SignIn, navOptions = navOptions {
                        popUpTo<Greeting> {
                            inclusive = true
                        }
                    })
                }
            )
        }
        composable<SignIn> {
            SignInScreen(
                onSubmit = {
                    navController.navigate(App, navOptions = navOptions {
                        popUpTo<SignIn> {
                            inclusive = true
                        }
                    })
                }, onClickSignUp = {
                    navController.navigate(SignUp, navOptions = navOptions {
                        popUpTo<SignIn> {
                            inclusive = true
                        }
                    })
                }
            )
        }
        composable<SignUp> {
            SignUpScreen(
                onSubmit = {
                    navController.navigate(SignIn, navOptions = navOptions {
                        popUpTo<SignUp> {
                            inclusive = true
                        }
                    })
                },
                onClickReturn = {
                    navController.navigate(SignIn, navOptions = navOptions {
                        popUpTo<SignUp> {
                            inclusive = true
                        }
                    })
                }
            )
        }
        main(navController)
    }

}

private fun NavGraphBuilder.main(navController: NavHostController) {
    navigation<App>(startDestination = App.SavedRecipes) {
        composable<App.Home> {

        }
        composable<App.SavedRecipes> {
            val viewModel =
                viewModel<SavedRecipesViewModel>(factory = SavedRecipesViewModel.Factory)
            val state by viewModel.savedRecipes.collectAsStateWithLifecycle()

            SavedRecipesScreen(state = state)
        }
    }
}