package com.surivalcoding.composerecipeapp.domain.repository.di

import com.surivalcoding.composerecipeapp.data.datasource.RecipeDataSource
import com.surivalcoding.composerecipeapp.data.datasource.UserDataSource
import com.surivalcoding.composerecipeapp.data.test.DemoNetworkDataSource
import com.surivalcoding.composerecipeapp.data.test.RecipeRepositoryImpl
import com.surivalcoding.composerecipeapp.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RecipeRepositoryModule {

    @Binds
    fun bindRecipeRepository(
        recipeRepositoryImpl: RecipeRepositoryImpl,
    ): RecipeRepository

    @Binds
    fun bindRecipeDataSource(
        recipeDataSourceImpl: DemoNetworkDataSource,
    ): RecipeDataSource

    @Binds
    fun bindUserDataSource(
        userDataSourceImpl: DemoNetworkDataSource,
    ): UserDataSource

}