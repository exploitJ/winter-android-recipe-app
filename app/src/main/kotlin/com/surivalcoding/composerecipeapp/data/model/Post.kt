package com.surivalcoding.composerecipeapp.data.model

import io.ktor.http.*
import kotlinx.datetime.LocalDateTime
import java.util.UUID

data class Post<T>(
    val id: UUID,
    val owner: User,
    val title: String,
    val comments: List<Comment>,
    val shareableLink: Url,
    val editedAt: LocalDateTime,
    val createdAt: LocalDateTime,
    val media: List<Media>,
    val content: T,
)