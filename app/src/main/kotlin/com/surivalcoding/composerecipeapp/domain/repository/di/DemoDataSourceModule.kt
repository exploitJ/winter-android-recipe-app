package com.surivalcoding.composerecipeapp.domain.repository.di

import com.surivalcoding.composerecipeapp.data.datasource.NotificationDataSource
import com.surivalcoding.composerecipeapp.data.datasource.RecipeDataSource
import com.surivalcoding.composerecipeapp.data.datasource.UserDataSource
import com.surivalcoding.composerecipeapp.data.test.DemoNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DemoDataSourceModule {

    @Binds
    fun bindRecipeDataSource(
        recipeDataSourceImpl: DemoNetworkDataSource,
    ): RecipeDataSource

    @Binds
    fun bindUserDataSource(
        userDataSourceImpl: DemoNetworkDataSource,
    ): UserDataSource

    @Binds
    fun bindNotificationDataSource(
        notificationDataSourceImpl: DemoNetworkDataSource,
    ): NotificationDataSource
}