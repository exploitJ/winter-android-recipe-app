package com.surivalcoding.composerecipeapp.data.util


import coil3.Uri
import coil3.toUri
import com.surivalcoding.composerecipeapp.data.dto.CommentDto
import com.surivalcoding.composerecipeapp.data.dto.IngredientDto
import com.surivalcoding.composerecipeapp.data.dto.MediaDto
import com.surivalcoding.composerecipeapp.data.dto.MinimalPostDto
import com.surivalcoding.composerecipeapp.data.dto.MinimalUserDto
import com.surivalcoding.composerecipeapp.data.dto.NotificationDto
import com.surivalcoding.composerecipeapp.data.dto.RecipeDto
import com.surivalcoding.composerecipeapp.data.dto.RecipePostDto
import com.surivalcoding.composerecipeapp.data.dto.StepsDto
import com.surivalcoding.composerecipeapp.data.dto.UserDto
import com.surivalcoding.composerecipeapp.data.model.Comment
import com.surivalcoding.composerecipeapp.data.model.Email
import com.surivalcoding.composerecipeapp.data.model.Ingredient
import com.surivalcoding.composerecipeapp.data.model.Media
import com.surivalcoding.composerecipeapp.data.model.Notification
import com.surivalcoding.composerecipeapp.data.model.Post
import com.surivalcoding.composerecipeapp.data.model.PostId
import com.surivalcoding.composerecipeapp.data.model.Recipe
import com.surivalcoding.composerecipeapp.data.model.Tag
import com.surivalcoding.composerecipeapp.data.model.User
import com.surivalcoding.composerecipeapp.data.model.UserId
import io.ktor.http.Url
import kotlinx.datetime.Instant
import java.util.UUID

fun UserDto.toDomainModel(): User = User(
    id = UserId(UUID.fromString(id)),
    memberSince = memberSince,
    nickname = nickname,
    fullName = fullName,
    email = Email(email),
    profileImage = profileImage.toDomainModel(),
    following = following.map { it.toDomainModel() }.toSet(),
    followers = followers.map { it.toDomainModel() }.toSet(),
    bio = bio,
    occupation = occupation,
    address = address,
    savedPosts = savedPosts.map { PostId(UUID.fromString(it)) }
)

fun MinimalUserDto.toDomainModel(): User = User(
    id = UserId(UUID.fromString(id)),
    memberSince = Instant.fromEpochMilliseconds(0), // Placeholder as minimal doesn't include this
    nickname = nickname,
    fullName = "", // Minimal version doesn't include this
    email = Email(""), // Minimal version doesn't include this
    profileImage = profileImage.toDomainModel(),
    following = emptySet(),
    followers = emptySet(),
    bio = "",
    occupation = "",
    address = "",
    savedPosts = emptyList()
)

fun MediaDto.toDomainModel(): Media = when (this.type) {
    "image" -> Media.Image(url.toUri())
    "video" -> Media.Video(url.toUri(), thumbnail?.toDomainModel() as Media.Image)
    else -> Media.Image(url.toUri())
}

fun RecipePostDto.toDomainModel(): Post<Recipe> = Post(
    id = PostId(UUID.fromString(id)),
    author = author.toDomainModel(),
    title = title,
    comments = comments.map { it.toDomainModel() },
    shareableLink = Url(shareableLink),
    editedAt = editedAt,
    createdAt = createdAt,
    thumbnail = thumbnail?.toDomainModel() as? Media.Image,
    media = media.map { it.toDomainModel() },
    content = content.toDomainModel()
)

fun RecipeDto.toDomainModel(): Recipe = Recipe(
    ingredients = ingredients.map { it.toDomainModel() },
    instruction = instruction.map { it.toDomainModel() },
    starRating = starRating,
    cookingTimeInMinutes = cookingTimeInMinutes,
    servings = servings,
    tags = tags.map { Tag(it) }.toSet()
)

fun IngredientDto.toDomainModel(): Ingredient = Ingredient(
    name = name,
    shortName = shortName,
    thumbnail = thumbnail.toDomainModel() as Media.Image,
    amountInGrams = amountInGrams
)

fun StepsDto.toDomainModel(): Recipe.Steps = Recipe.Steps(
    title = title,
    description = description
)

fun CommentDto.toDomainModel(): Comment = Comment(
    userId = userId,
    content = content,
    likes = likes.map { it.toDomainModel() }.toSet(),
    dislike = dislike.map { it.toDomainModel() }.toSet(),
    editedAt = editedAt,
    createdAt = createdAt
)

fun NotificationDto.toDomainModel(): Notification = Notification(
    title = title,
    contents = contents,
    sentAt = sentAt,
    recipient = recipient.toDomainModel(),
    isRead = isRead,
    linkedPost = linkedPost?.toDomainModel()
        ?: throw IllegalStateException("Linked post is required")
)

fun MinimalPostDto.toDomainModel(): Post<Unit> = Post(
    id = PostId(UUID.fromString(id)),
    author = User( // Minimal post doesn't include author details
        id = UserId(UUID.randomUUID()),
        memberSince = Instant.fromEpochMilliseconds(0),
        nickname = "",
        fullName = "",
        email = Email(""),
        profileImage = Media.Image(Uri()),
        following = emptySet(),
        followers = emptySet(),
        bio = "",
        occupation = "",
        address = "",
        savedPosts = emptyList()
    ),
    title = title,
    comments = emptyList(),
    shareableLink = Url("https://example.com"),
    editedAt = Instant.fromEpochMilliseconds(0),
    createdAt = Instant.fromEpochMilliseconds(0),
    thumbnail = thumbnail?.toDomainModel() as? Media.Image,
    content = Unit
)