package com.surivalcoding.composerecipeapp.data.test

import com.surivalcoding.composerecipeapp.data.datasource.UserDataSource
import com.surivalcoding.composerecipeapp.data.util.toDomainModel
import com.surivalcoding.composerecipeapp.domain.model.Email
import com.surivalcoding.composerecipeapp.domain.model.Post
import com.surivalcoding.composerecipeapp.domain.model.User
import com.surivalcoding.composerecipeapp.domain.repository.UserInfoRepository
import java.util.UUID
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val userSource: UserDataSource,
) : UserInfoRepository {
    override suspend fun getCurrentUser(): User {
        return userSource.getUser(UUID.randomUUID()).getOrThrow().toDomainModel()
    }

    override suspend fun getAllUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun <T> findUserByPost(post: Post<out T>): User {
        TODO("Not yet implemented")
    }

    override suspend fun findUserByEmail(email: Email): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun createNewUser(user: User): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun deregister(user: User): Result<UUID> {
        TODO("Not yet implemented")
    }

    override suspend fun editUser(newData: User): Result<User> {
        TODO("Not yet implemented")
    }
}