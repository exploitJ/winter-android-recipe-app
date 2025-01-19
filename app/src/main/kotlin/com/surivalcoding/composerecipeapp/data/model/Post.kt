package com.surivalcoding.composerecipeapp.data.model

import io.ktor.http.Url
import kotlinx.datetime.Instant
import java.util.UUID

data class Post<T>(
    val id: PostId,
    val author: User,
    val title: String,
    val comments: List<Comment>,
    val shareableLink: Url,
    val editedAt: Instant,
    val createdAt: Instant,
    val thumbnail: Media.Image?,
    val media: List<Media> = emptyList(),
    val content: T,
)

@JvmInline
value class PostId(val id: UUID)
