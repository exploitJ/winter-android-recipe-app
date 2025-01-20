package com.surivalcoding.composerecipeapp.data.datasource

import com.surivalcoding.composerecipeapp.data.dto.RecipeRequestBody
import com.surivalcoding.composerecipeapp.data.dto.RecipeResponse
import com.surivalcoding.composerecipeapp.data.model.PostId

interface RecipeDataSource {
    suspend fun createRecipe(id: PostId): Result<RecipeResponse>
    suspend fun getRecipe(id: PostId): Result<RecipeResponse>
    suspend fun getAll(): Result<RecipeResponse>
    suspend fun updateRecipe(id: PostId, requestBody: RecipeRequestBody): Result<RecipeResponse>
    suspend fun deleteRecipe(id: PostId): Result<RecipeResponse>
}