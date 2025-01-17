package com.surivalcoding.composerecipeapp.data.repository

import com.surivalcoding.composerecipeapp.data.model.Post
import com.surivalcoding.composerecipeapp.data.model.Recipe
import com.surivalcoding.composerecipeapp.data.model.Tag
import java.util.UUID


interface RecipeRepository {
    suspend fun getRecentPosts(amount: Int): List<Post<Recipe>>
    suspend fun <T> getSorted(comparator: Comparator<T>): List<Post<Recipe>>
    suspend fun findPostByAuthor(userId: UUID): Set<Post<Recipe>>
    suspend fun getFiltered(tags: Array<Tag> = arrayOf(Tag("all"))): Set<Post<Recipe>>
    suspend fun findByStarRating(range: IntRange): Set<Post<Recipe>>
    suspend fun findBySearchTerm(vararg keywords: String): Set<Post<Recipe>>

    suspend fun createRecipe(content: Post<Recipe>): Result<Post<Recipe>>
    suspend fun editRecipe(id: UUID, newPost: Post<Recipe>): Result<Post<Recipe>>
    suspend fun deleteRecipe(id: UUID): Result<Post<Recipe>>
}