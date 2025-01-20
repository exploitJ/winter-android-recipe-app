package com.surivalcoding.composerecipeapp

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.request.crossfade
import com.surivalcoding.composerecipeapp.data.test.DemoNetworkRecipeDataSource
import com.surivalcoding.composerecipeapp.data.test.DemoNetworkUserDataSource
import com.surivalcoding.composerecipeapp.data.test.FakeRecipeRepository
import com.surivalcoding.composerecipeapp.data.test.FakeUserInfoRepository

class RecipeApplication : Application(), SingletonImageLoader.Factory {
    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader.Builder(context)
            .crossfade(true)
            .build()
    }

    private val recipeInputStream by lazy { baseContext.resources.openRawResource(R.raw.recipe) }
    private val userInputStream by lazy { baseContext.resources.openRawResource(R.raw.user) }
    private val userDataSource by lazy { DemoNetworkUserDataSource(userInputStream) }
    private val recipeDataSource by lazy { DemoNetworkRecipeDataSource(recipeInputStream) }

    val userRepository by lazy { FakeUserInfoRepository(userDataSource) }
    val recipeRepository by lazy {
        FakeRecipeRepository(
            recipeDataSource,
            userDataSource
        )
    }

}