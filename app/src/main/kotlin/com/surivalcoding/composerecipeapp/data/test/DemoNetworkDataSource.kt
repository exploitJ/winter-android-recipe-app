package com.surivalcoding.composerecipeapp.data.test

import com.surivalcoding.composerecipeapp.R
import com.surivalcoding.composerecipeapp.data.datasource.NetworkApiDataSource
import com.surivalcoding.composerecipeapp.data.dto.NotificationsResponse
import com.surivalcoding.composerecipeapp.data.dto.RecipeResponse
import com.surivalcoding.composerecipeapp.data.dto.UserDto
import com.surivalcoding.composerecipeapp.data.dto.UserResponse
import com.surivalcoding.composerecipeapp.data.util.DemoResponseManager
import com.surivalcoding.composerecipeapp.domain.model.UserId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream
import javax.inject.Inject

class DemoNetworkDataSource @Inject constructor(
    private val demoResponseManager: DemoResponseManager,
) : NetworkApiDataSource {
    private var recipeResponse: RecipeResponse? = null
    private var userResponse: UserResponse? = null
    private var notificationsResponse: NotificationsResponse? = null

    override suspend fun getUser(id: UserId): Result<UserDto> {
        userResponse?.let { return Result.success(it.getCurrentUser) }

        val data = parseJson<UserResponse>(demoResponseManager.open(R.raw.user))
        userResponse = data
        return Result.success(data.getCurrentUser)
    }

    override suspend fun getNotification(): Result<NotificationsResponse> {
        notificationsResponse?.let { return Result.success(it) }

        val data = parseJson<NotificationsResponse>(demoResponseManager.open(R.raw.notification))
        notificationsResponse = data
        return Result.success(data)
    }

    override suspend fun getRecipe(): Result<RecipeResponse> {
        recipeResponse?.let { return Result.success(it) }

        val data = parseJson<RecipeResponse>(demoResponseManager.open(R.raw.recipe))
        recipeResponse = data
        return Result.success(data)
    }

    @OptIn(ExperimentalSerializationApi::class)
    private suspend inline fun <reified T> parseJson(stream: InputStream): T =
        withContext(Dispatchers.IO) {
            stream.use {
                Json.decodeFromStream<T>(it)
            }
        }
}