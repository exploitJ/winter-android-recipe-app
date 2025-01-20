package com.surivalcoding.composerecipeapp.data.test

import com.surivalcoding.composerecipeapp.data.datasource.RecipeDataSource
import com.surivalcoding.composerecipeapp.data.dto.RecipeRequestBody
import com.surivalcoding.composerecipeapp.data.dto.RecipeResponse
import com.surivalcoding.composerecipeapp.data.model.PostId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream

class DemoNetworkRecipeDataSource(private val stream: InputStream) : RecipeDataSource {
    private var mocked: RecipeResponse? = null
    override suspend fun createRecipe(id: PostId): Result<RecipeResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipe(id: PostId): Result<RecipeResponse> {
        TODO("Not yet implemented")
    }


    override suspend fun getAll(): Result<RecipeResponse> {
        mocked?.let { return Result.success(it) }

        val data = parseRecipe(stream)
        mocked = data
        return Result.success(data)
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
    private suspend fun parseRecipe(stream: InputStream): RecipeResponse =
        withContext(Dispatchers.IO) {
            Json.decodeFromStream<RecipeResponse>(stream)
        }
}