package com.surivalcoding.composerecipeapp.data.datasource

import com.surivalcoding.composerecipeapp.data.dto.UserQueryParameter
import com.surivalcoding.composerecipeapp.data.dto.UserResponse
import com.surivalcoding.composerecipeapp.data.model.UserId

interface UserDataSource {
    suspend fun createUser(id: UserId): Result<UserResponse>
    suspend fun getUser(id: UserId): Result<UserResponse>
    suspend fun updateUser(id: UserId, userQueryParameter: UserQueryParameter): Result<UserResponse>
    suspend fun deleteUser(id: UserId): Result<UserResponse>
}