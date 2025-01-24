package com.surivalcoding.composerecipeapp.data.datasource

import com.surivalcoding.composerecipeapp.data.dto.UserDto
import com.surivalcoding.composerecipeapp.domain.model.UserId

interface UserDataSource {
    suspend fun getUser(id: UserId): Result<UserDto>
}