package com.surivalcoding.composerecipeapp.data.datasource

import com.surivalcoding.composerecipeapp.data.dto.UserQueryParameter
import com.surivalcoding.composerecipeapp.data.dto.UserResponse
import java.util.UUID

interface UserDataSource {
    suspend fun createUser(id: UUID): Result<UserResponse>
    suspend fun getUser(id: UUID): Result<UserResponse>
    suspend fun updateUser(id: UUID, userQueryParameter: UserQueryParameter): Result<UserResponse>
    suspend fun deleteUser(id: UUID): Result<UserResponse>
}