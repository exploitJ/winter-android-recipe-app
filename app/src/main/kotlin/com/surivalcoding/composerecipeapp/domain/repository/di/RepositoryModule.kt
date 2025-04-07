package com.surivalcoding.composerecipeapp.domain.repository.di

import com.surivalcoding.composerecipeapp.data.test.NotificationRepositoryImpl
import com.surivalcoding.composerecipeapp.data.test.RecipeRepositoryImpl
import com.surivalcoding.composerecipeapp.data.test.UserInfoRepositoryImpl
import com.surivalcoding.composerecipeapp.domain.repository.NotificationRepository
import com.surivalcoding.composerecipeapp.domain.repository.RecipeRepository
import com.surivalcoding.composerecipeapp.domain.repository.UserInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindRecipeRepository(
        recipeRepositoryImpl: RecipeRepositoryImpl,
    ): RecipeRepository

    @Binds
    fun bindUserInfoRepository(
        recipeRepositoryImpl: UserInfoRepositoryImpl,
    ): UserInfoRepository

    @Binds
    fun bindNotificationRepository(
        notificationRepositoryImpl: NotificationRepositoryImpl,
    ): NotificationRepository

}