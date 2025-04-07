package com.surivalcoding.composerecipeapp.domain.repository

import com.surivalcoding.composerecipeapp.domain.model.Post
import com.surivalcoding.composerecipeapp.domain.model.PostId
import com.surivalcoding.composerecipeapp.domain.model.Recipe
import com.surivalcoding.composerecipeapp.domain.model.Tag
import com.surivalcoding.composerecipeapp.domain.model.UserId


interface RecipeRepository {
    suspend fun getAll(): Set<Post<Recipe>>
    suspend fun getSavedRecipes(id: UserId): List<Post<Recipe>>
    suspend fun getRecentPosts(amount: Int): List<Post<Recipe>>
    suspend fun <T> getSorted(comparator: Comparator<T>): List<Post<Recipe>>
    suspend fun findPostByAuthor(id: UserId): Set<Post<Recipe>>
    suspend fun getFiltered(tags: Array<Tag> = arrayOf(Tag("all"))): Set<Post<Recipe>>
    suspend fun findByStarRating(range: IntRange): Set<Post<Recipe>>
    suspend fun findBySearchTerm(vararg keywords: String): List<Post<Recipe>>

    suspend fun createRecipe(content: Post<Recipe>): Result<Post<Recipe>>
    suspend fun editRecipe(id: PostId, newPost: Post<Recipe>): Result<Post<Recipe>>
    suspend fun deleteRecipe(id: PostId): Result<Post<Recipe>>
}