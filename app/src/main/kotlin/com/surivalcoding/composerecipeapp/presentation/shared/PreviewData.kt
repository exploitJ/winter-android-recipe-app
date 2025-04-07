package com.surivalcoding.composerecipeapp.presentation.shared

import coil3.toUri
import com.surivalcoding.composerecipeapp.domain.model.Comment
import com.surivalcoding.composerecipeapp.domain.model.Email
import com.surivalcoding.composerecipeapp.domain.model.Ingredient
import com.surivalcoding.composerecipeapp.domain.model.Media
import com.surivalcoding.composerecipeapp.domain.model.Notification
import com.surivalcoding.composerecipeapp.domain.model.Post
import com.surivalcoding.composerecipeapp.domain.model.Recipe
import com.surivalcoding.composerecipeapp.domain.model.Tag
import com.surivalcoding.composerecipeapp.domain.model.User
import io.ktor.http.Url
import kotlinx.datetime.Clock
import java.util.UUID

object PreviewData {

    val mediaImage = Media.Image(url = "https://example.com/sample-image.jpg".toUri())
    val mediaVideo = Media.Video(
        url = "https://example.com/sample-video.mp4".toUri(),
        thumbnail = mediaImage,
    )

    val uuid: UUID = UUID.fromString("550e8400-e29b-41d4-a716-446655440000")

    val ingredient = Ingredient(
        name = "All-purpose flour",
        shortName = "flour",
        thumbnail = mediaImage,
        amountInGrams = 250
    )

    val comment = Comment(
        userId = 1,
        content = "This looks amazing!",
        likes = setOf(),
        dislike = emptySet(),
        editedAt = Clock.System.now(),
        createdAt = Clock.System.now()
    )

    private val recipeSteps = Recipe.Steps(
        title = "Prepare ingredients",
        description = "Measure all ingredients and bring eggs to room temperature"
    )

    val recipe = Recipe(
        ingredients = MutableList(2) { ingredient },
        instruction = MutableList(4) { recipeSteps },
        starRating = 4.5f,
        cookingTimeInMinutes = 45,
        servings = 4,
        tags = setOf(Tag("Baking"))
    )


    val user = User(
        id = uuid,
        memberSince = Clock.System.now(),
        nickname = "chef_john",
        fullName = "John Smith",
        email = Email("john.smith@example.com"),
        profileImage = mediaImage,
        following = emptySet(),
        followers = emptySet(),
        bio = "Passionate about cooking and sharing recipes.Passionate about cooking and sharing recipes.Passionate about cooking and sharing recipes.Passionate about cooking and sharing recipes.",
        occupation = "Professional Chef",
        address = "New York, NY",
        savedPosts = emptyList()
    )

    val postRecipe = Post(
        id = uuid,
        author = user,
        title = "Perfect Homemade Chocolate Cake",
        comments = listOf(comment),
        shareableLink = Url("https://example.com/recipe/${uuid}"),
        editedAt = Clock.System.now(),
        createdAt = Clock.System.now(),
        thumbnail = mediaImage,
        media = listOf(mediaImage),
        content = recipe,
    )

    val notification = Notification(
        title = "New Recipe Alert",
        contents = "Check out our latest recipe!",
        sentAt = Clock.System.now(),
        recipient = user,
        isRead = false,
        linkedPost = postRecipe,
    )
}