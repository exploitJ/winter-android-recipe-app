package com.surivalcoding.composerecipeapp.data.datasource

import com.surivalcoding.composerecipeapp.data.dto.RecipeRequestBody
import com.surivalcoding.composerecipeapp.data.dto.RecipeResponse
import java.util.UUID

interface RecipeDataSource {
    suspend fun createRecipe(id: UUID): Result<RecipeResponse>
    suspend fun getRecipe(id: UUID): Result<RecipeResponse>
    suspend fun getAll(): Result<RecipeResponse>
    suspend fun updateRecipe(id: UUID, requestBody: RecipeRequestBody): Result<RecipeResponse>
    suspend fun deleteRecipe(id: UUID): Result<RecipeResponse>
}