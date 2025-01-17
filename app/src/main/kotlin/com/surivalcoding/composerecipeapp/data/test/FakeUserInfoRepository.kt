package com.surivalcoding.composerecipeapp.data.test

import com.surivalcoding.composerecipeapp.data.model.Email
import com.surivalcoding.composerecipeapp.data.model.Post
import com.surivalcoding.composerecipeapp.data.model.User
import com.surivalcoding.composerecipeapp.data.repository.UserInfoRepository
import java.util.UUID

class FakeUserInfoRepository : UserInfoRepository {
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