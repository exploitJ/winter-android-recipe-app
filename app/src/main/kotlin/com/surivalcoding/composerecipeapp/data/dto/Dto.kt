package com.surivalcoding.composerecipeapp.data.dto

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponse(
    val getRecipes: List<RecipePostDto>
)

@Serializable
data class NotificationsResponse(
    val getNotifications: List<NotificationDto>
)

@Serializable
data class UserResponse(
    val getCurrentUser: UserDto
)

@Serializable
data class UserDto(
    val id: String,
    val memberSince: Instant,
    val nickname: String,
    val fullName: String,
    val email: String,
    val profileImage: MediaDto,
    val following: List<MinimalUserDto> = emptyList(),
    val followers: List<MinimalUserDto> = emptyList(),
    val bio: String,
    val occupation: String,
    val address: String,
    val savedPosts: List<String> = emptyList()
)

@Serializable
data class MinimalUserDto(
    val id: String,
    val nickname: String,
    val profileImage: MediaDto
)

@Serializable
data class MediaDto(
    val type: String,
    val url: String,
    val thumbnail: MediaDto? = null
)

@Serializable
data class RecipePostDto(
    val id: String,
    val author: MinimalUserDto,
    val title: String,
    val comments: List<CommentDto>,
    val shareableLink: String,
    val editedAt: Instant,
    val createdAt: Instant,
    val thumbnail: MediaDto?,
    val media: List<MediaDto> = emptyList(),
    val content: RecipeDto
)

@Serializable
data class RecipeDto(
    val ingredients: List<IngredientDto>,
    val instruction: List<StepsDto>,
    val starRating: Float,
    val cookingTimeInMinutes: Int,
    val servings: Int,
    val tags: List<String>
)

@Serializable
data class IngredientDto(
    val name: String,
    val shortName: String,
    val thumbnail: MediaDto,
    val amountInGrams: Int
)

@Serializable
data class StepsDto(
    val title: String,
    val description: String
)

@Serializable
data class CommentDto(
    val userId: Int,
    val content: String,
    val likes: List<MinimalUserDto> = emptyList(),
    val dislike: List<MinimalUserDto> = emptyList(),
    val editedAt: Instant,
    val createdAt: Instant
)

@Serializable
data class NotificationDto(
    val title: String,
    val contents: String,
    val sentAt: Instant,
    val recipient: MinimalUserDto,
    val isRead: Boolean,
    val linkedPost: MinimalPostDto?
)

@Serializable
data class MinimalPostDto(
    val id: String,
    val title: String,
    val thumbnail: MediaDto?
)

