package com.surivalcoding.composerecipeapp.data.model

import kotlinx.datetime.LocalDateTime

data class Comment(
    val userId: Int,
    val content: String,
    val likes: Set<User>,
    val dislike: Set<User>,
    val editedAt: LocalDateTime,
    val createdAt: LocalDateTime,
)