package com.surivalcoding.composerecipeapp.data.model

import kotlinx.datetime.Instant

data class Comment(
    val userId: Int,
    val content: String,
    val likes: Set<User>,
    val dislike: Set<User>,
    val editedAt: Instant,
    val createdAt: Instant,
)