package com.surivalcoding.composerecipeapp.presentation.screen.searchrecipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Serializable
data object SearchRecipeRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRecipeScreen(
    modifier: Modifier = Modifier,
    state: SearchRecipesState,
) {
    Scaffold(
        modifier = modifier
            .background(color = Color.White)
            .padding(PaddingValues(12.dp).calculateTopPadding()),
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Search Recipes") },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                })
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            SearchRecipeSearchBar()
            SearchRecipeResults(state = state)
            SearchRecipeRecentSearches(state = state)
        }

    }


}

@Composable
fun SearchRecipeRecentSearches(state: SearchRecipesState) {

}

@Composable
fun SearchRecipeResults(state: SearchRecipesState) {

}

@Composable
fun SearchRecipeSearchBar() {
}

@Preview
@Composable
private fun SearchRecipePreview() {

}