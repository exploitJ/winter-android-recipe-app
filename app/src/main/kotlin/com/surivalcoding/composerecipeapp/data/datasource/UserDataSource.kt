package com.surivalcoding.composerecipeapp.data.datasource

import com.surivalcoding.composerecipeapp.data.dto.UserQueryParameter
import com.surivalcoding.composerecipeapp.data.dto.UserResponse
import java.util.*

interface UserDataSource {
    fun createUser(id: UUID): Result<UserResponse>
    fun getUser(id: UUID): Result<UserResponse>
    fun updateUser(id: UUID, userQueryParameter: UserQueryParameter): Result<UserResponse>
    fun deleteUser(id: UUID): Result<UserResponse>
}