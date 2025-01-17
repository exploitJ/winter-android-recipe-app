package com.surivalcoding.composerecipeapp.data.test

import com.surivalcoding.composerecipeapp.data.datasource.RecipeDataSource
import com.surivalcoding.composerecipeapp.data.repository.RecipeRepository

class FakeRecipeRepository(
    val recipeDataSource: RecipeDataSource
) : RecipeRepository {


}
