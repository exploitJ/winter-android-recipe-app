package com.surivalcoding.composerecipeapp.data.repository

import com.surivalcoding.composerecipeapp.data.model.Email
import com.surivalcoding.composerecipeapp.data.model.Post
import com.surivalcoding.composerecipeapp.data.model.User
import java.util.*

interface UserInfoRepository {
    fun getAllUsers(): List<User>
    fun <T> findUserByPost(post: Post<out T>): User
    fun findUserByEmail(email: Email): Result<User>

    fun createNewUser(user: User): Result<User>
    fun deregister(user: User): Result<UUID>
    fun editUser(newData: User): Result<User>
}