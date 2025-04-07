package com.surivalcoding.composerecipeapp.domain.model

import kotlinx.datetime.Instant
import java.util.UUID

data class User(
    val id: UserId,
    val memberSince: Instant,
    val nickname: String,
    val fullName: String,
    val email: Email,
    val profileImage: Media.Image,
    val following: Set<User>,
    val followers: Set<User>,
    val bio: String,
    val occupation: String,
    val address: String,
    val savedPosts: List<PostId>,
)

@JvmInline
value class Email(private val address: String) {
}

typealias UserId = UUID