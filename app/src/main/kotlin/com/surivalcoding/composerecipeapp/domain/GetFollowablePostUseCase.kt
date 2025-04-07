package com.surivalcoding.composerecipeapp.domain

import com.surivalcoding.composerecipeapp.domain.model.Post
import com.surivalcoding.composerecipeapp.domain.model.Recipe
import com.surivalcoding.composerecipeapp.domain.repository.RecipeRepository
import com.surivalcoding.composerecipeapp.domain.repository.UserInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetFollowingPostUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
    private val recipeRepository: RecipeRepository,
) {
    operator fun invoke(): Flow<List<Post<Recipe>>> {
        return flow {
            val user = userInfoRepository.getCurrentUser()
            emit(
                recipeRepository.getSavedRecipes(user.id)
            )
        }.flowOn(Dispatchers.IO)
    }

}