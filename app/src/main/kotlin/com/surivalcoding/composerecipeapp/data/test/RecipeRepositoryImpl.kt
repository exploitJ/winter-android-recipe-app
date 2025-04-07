package com.surivalcoding.composerecipeapp.data.test

import com.surivalcoding.composerecipeapp.data.datasource.RecipeDataSource
import com.surivalcoding.composerecipeapp.data.datasource.UserDataSource
import com.surivalcoding.composerecipeapp.data.util.toDomainModel
import com.surivalcoding.composerecipeapp.domain.model.Post
import com.surivalcoding.composerecipeapp.domain.model.PostId
import com.surivalcoding.composerecipeapp.domain.model.Recipe
import com.surivalcoding.composerecipeapp.domain.model.Tag
import com.surivalcoding.composerecipeapp.domain.model.UserId
import com.surivalcoding.composerecipeapp.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val recipeDataSource: RecipeDataSource,
) : RecipeRepository {
    override suspend fun getAll(): Set<Post<Recipe>> = getRecipes()

    override suspend fun getSavedRecipes(id: UserId): List<Post<Recipe>> {
        val currentUser = userDataSource.getUser(id).getOrNull() ?: return emptyList()
        val recipes = getRecipes()

        val savedPosts = currentUser.toDomainModel().savedPosts
        return recipes.filter {
            savedPosts.contains(it.id)
        }
    }

    override suspend fun getRecentPosts(amount: Int): List<Post<Recipe>> {
        val responses = recipeDataSource.getRecipe().getOrNull()?.getRecipes ?: emptyList()
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
        val responses = recipeDataSource.getRecipe().getOrNull()?.getRecipes ?: emptyList()
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

    private suspend fun getRecipes(): Set<Post<Recipe>> {
        val response = recipeDataSource.getRecipe().getOrNull()?.getRecipes ?: emptyList()
        return response.map { it.toDomainModel() }
            .toSet()
    }
}

