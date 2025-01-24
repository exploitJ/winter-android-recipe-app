package com.surivalcoding.composerecipeapp.domain.repository

import com.surivalcoding.composerecipeapp.domain.model.Email
import com.surivalcoding.composerecipeapp.domain.model.Post
import com.surivalcoding.composerecipeapp.domain.model.User
import java.util.UUID

interface UserInfoRepository {
    suspend fun getCurrentUser(): User
    suspend fun getAllUsers(): List<User>
    suspend fun <T> findUserByPost(post: Post<out T>): User
    suspend fun findUserByEmail(email: Email): Result<User>

    suspend fun createNewUser(user: User): Result<User>
    suspend fun deregister(user: User): Result<UUID>
    suspend fun editUser(newData: User): Result<User>
}