package com.surivalcoding.composerecipeapp.data.repository

import com.surivalcoding.composerecipeapp.data.model.Post
import com.surivalcoding.composerecipeapp.data.model.PostId
import com.surivalcoding.composerecipeapp.data.model.Recipe
import com.surivalcoding.composerecipeapp.data.model.Tag
import com.surivalcoding.composerecipeapp.data.model.UserId


interface RecipeRepository {
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