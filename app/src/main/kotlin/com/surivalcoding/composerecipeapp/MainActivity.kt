package com.surivalcoding.composerecipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.surivalcoding.composerecipeapp.presentation.screen.mypage.SavedRecipesScreen
import com.surivalcoding.composerecipeapp.presentation.screen.mypage.SavedRecipesViewModel
import com.surivalcoding.composerecipeapp.ui.theme.ComposeRecipeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: SavedRecipesViewModel by viewModels { SavedRecipesViewModel.Factory }

        setContent {
            ComposeRecipeAppTheme {
                val state by viewModel.savedRecipes.collectAsStateWithLifecycle()
                SavedRecipesScreen(
                    state = state
                )
            }
        }
    }
}