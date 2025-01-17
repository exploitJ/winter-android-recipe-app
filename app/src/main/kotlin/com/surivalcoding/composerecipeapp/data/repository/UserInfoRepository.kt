package com.surivalcoding.composerecipeapp.data.repository

import com.surivalcoding.composerecipeapp.data.model.Email
import com.surivalcoding.composerecipeapp.data.model.Post
import com.surivalcoding.composerecipeapp.data.model.User
import java.util.UUID

interface UserInfoRepository {
    suspend fun getAllUsers(): List<User>
    suspend fun <T> findUserByPost(post: Post<out T>): User
    suspend fun findUserByEmail(email: Email): Result<User>

    suspend fun createNewUser(user: User): Result<User>
    suspend fun deregister(user: User): Result<UUID>
    suspend fun editUser(newData: User): Result<User>
}