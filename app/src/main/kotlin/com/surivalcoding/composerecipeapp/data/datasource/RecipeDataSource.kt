package com.surivalcoding.composerecipeapp.data.datasource

import com.surivalcoding.composerecipeapp.data.dto.RecipeRequestBody
import com.surivalcoding.composerecipeapp.data.dto.RecipeResponse
import java.util.*

interface RecipeDataSource {
    fun createRecipe(id: UUID): Result<RecipeResponse>
    fun getRecipe(id: UUID): Result<RecipeResponse>
    fun updateRecipe(id: UUID, requestBody: RecipeRequestBody): Result<RecipeResponse>
    fun deleteRecipe(id: UUID): Result<RecipeResponse>
}