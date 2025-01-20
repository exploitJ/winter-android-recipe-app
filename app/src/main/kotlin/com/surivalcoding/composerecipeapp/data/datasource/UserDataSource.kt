package com.surivalcoding.composerecipeapp.data.datasource

import com.surivalcoding.composerecipeapp.data.dto.UserDto
import com.surivalcoding.composerecipeapp.data.dto.UserQueryParameter
import com.surivalcoding.composerecipeapp.data.model.UserId

interface UserDataSource {
    suspend fun createUser(id: UserId): Result<UserDto>
    suspend fun getUser(id: UserId): Result<UserDto>
    suspend fun updateUser(id: UserId, userQueryParameter: UserQueryParameter): Result<UserDto>
    suspend fun deleteUser(id: UserId): Result<UserId>
}