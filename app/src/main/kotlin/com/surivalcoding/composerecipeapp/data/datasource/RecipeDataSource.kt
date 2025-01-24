package com.surivalcoding.composerecipeapp.data.datasource

import com.surivalcoding.composerecipeapp.data.dto.RecipeResponse

interface RecipeDataSource {
    suspend fun getRecipe(): Result<RecipeResponse>
}