package com.surivalcoding.composerecipeapp.data.test

import com.surivalcoding.composerecipeapp.data.model.Post
import com.surivalcoding.composerecipeapp.data.model.PostId
import com.surivalcoding.composerecipeapp.data.model.Recipe
import com.surivalcoding.composerecipeapp.data.model.Tag
import com.surivalcoding.composerecipeapp.data.model.UserId
import com.surivalcoding.composerecipeapp.data.repository.RecipeRepository
import com.surivalcoding.composerecipeapp.data.util.toDomainModel

class FakeRecipeRepository(
    private val recipeDataSource: DemoNetworkRecipeDataSource,
    private val userDataSource: DemoNetworkUserDataSource,
) : RecipeRepository {
    override suspend fun getSavedRecipes(id: UserId): List<Post<Recipe>> {
        val currentUser = userDataSource.getUser(id).getOrNull() ?: return emptyList()
        val responses = recipeDataSource.getAll().getOrNull()?.getRecipes ?: return emptyList()

        val savedPosts = currentUser.toDomainModel().savedPosts
        return responses.map { it.toDomainModel() }
            .filter {
                savedPosts.contains(it.id)
            }
    }


    override suspend fun getRecentPosts(amount: Int): List<Post<Recipe>> {
        val responses = recipeDataSource.getAll().getOrNull()?.getRecipes ?: emptyList()
        return responses.map { it.toDomainModel() }
            .sortedByDescending { it.createdAt }
            .take(amount)
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

    override suspend fun findBySearchTerm(vararg keywords: String): List<Post<Recipe>> {
        val responses = recipeDataSource.getAll().getOrNull()?.getRecipes ?: emptyList()
        return responses.map { it.toDomainModel() }
            .filter {
                keywords.fold(false) { acc, keyword ->
                    acc || it.title.contains(keyword)
                }
            }
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
