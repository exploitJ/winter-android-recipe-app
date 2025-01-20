package com.surivalcoding.composerecipeapp.data.test

import com.surivalcoding.composerecipeapp.data.datasource.RecipeDataSource
import com.surivalcoding.composerecipeapp.data.dto.RecipeRequestBody
import com.surivalcoding.composerecipeapp.data.dto.RecipeResponse
import com.surivalcoding.composerecipeapp.data.model.PostId
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream

class DemoNetworkRecipeDataSource(stream: InputStream) : RecipeDataSource {
    private var mocked: RecipeResponse

    init {
        mocked = parseRecipe(stream)
    }

    override suspend fun createRecipe(id: PostId): Result<RecipeResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipe(id: PostId): Result<RecipeResponse> {
        TODO("Not yet implemented")
    }


    override suspend fun getAll(): Result<RecipeResponse> {
        return Result.success(mocked)
    }

    override suspend fun updateRecipe(
        id: PostId,
        requestBody: RecipeRequestBody
    ): Result<RecipeResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRecipe(id: PostId): Result<RecipeResponse> {
        TODO("Not yet implemented")
    }


    @OptIn(ExperimentalSerializationApi::class)
    fun parseRecipe(stream: InputStream): RecipeResponse =
        Json.decodeFromStream<RecipeResponse>(stream)
}