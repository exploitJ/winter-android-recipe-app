package com.surivalcoding.composerecipeapp.data.test

import com.surivalcoding.composerecipeapp.data.datasource.UserDataSource
import com.surivalcoding.composerecipeapp.data.model.Post
import com.surivalcoding.composerecipeapp.data.model.PostId
import com.surivalcoding.composerecipeapp.data.model.Recipe
import com.surivalcoding.composerecipeapp.data.model.Tag
import com.surivalcoding.composerecipeapp.data.model.UserId
import com.surivalcoding.composerecipeapp.data.repository.RecipeRepository

class FakeRecipeRepository(
    private val recipeDataSource: DemoNetworkRecipeDataSource,
    private val userDataSource: UserDataSource,
) : RecipeRepository {

    override suspend fun getRecentPosts(amount: Int): List<Post<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun <T> getSorted(comparator: Comparator<T>): List<Post<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun findPostByAuthor(id: UserId): Set<Post<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFiltered(tags: Array<Tag>): Set<Post<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun findByStarRating(range: IntRange): Set<Post<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun findBySearchTerm(vararg keywords: String): Set<Post<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun createRecipe(content: Post<Recipe>): Result<Post<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun editRecipe(id: PostId, newPost: Post<Recipe>): Result<Post<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRecipe(id: PostId): Result<Post<Recipe>> {
        TODO("Not yet implemented")
    }

}
