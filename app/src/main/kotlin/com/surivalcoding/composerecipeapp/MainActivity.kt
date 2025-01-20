package com.surivalcoding.composerecipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.surivalcoding.composerecipeapp.presentation.screen.mypage.SavedRecipesScreen
import com.surivalcoding.composerecipeapp.presentation.screen.mypage.SavedRecipesState
import com.surivalcoding.composerecipeapp.ui.theme.ComposeRecipeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeRecipeAppTheme {

                SavedRecipesScreen(
                    state = SavedRecipesState(
                    )
                )
            }
        }
    }
}