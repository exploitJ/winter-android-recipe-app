package com.surivalcoding.composerecipeapp.data.test

import com.surivalcoding.composerecipeapp.data.datasource.UserDataSource
import com.surivalcoding.composerecipeapp.data.dto.UserDto
import com.surivalcoding.composerecipeapp.data.dto.UserQueryParameter
import com.surivalcoding.composerecipeapp.data.dto.UserResponse
import com.surivalcoding.composerecipeapp.data.model.UserId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream

class DemoNetworkUserDataSource(private val stream: InputStream) : UserDataSource {
    private var mocked: UserResponse? = null
    override suspend fun createUser(id: UserId): Result<UserDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(id: UserId): Result<UserDto> {
        mocked?.let { return Result.success(it.getCurrentUser) }

        val data = parse(stream)
        mocked = data
        return Result.success(data.getCurrentUser)
    }

    override suspend fun updateUser(
        id: UserId,
        userQueryParameter: UserQueryParameter
    ): Result<UserDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(id: UserId): Result<UserId> {
        TODO("Not yet implemented")
    }

    @OptIn(ExperimentalSerializationApi::class)
    private suspend fun parse(stream: InputStream) = withContext(Dispatchers.IO) {
        Json.decodeFromStream<UserResponse>(stream)
    }
}
