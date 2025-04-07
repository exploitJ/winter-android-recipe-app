package com.surivalcoding.composerecipeapp.data.test

import com.surivalcoding.composerecipeapp.data.datasource.NotificationDataSource
import com.surivalcoding.composerecipeapp.data.util.toDomainModel
import com.surivalcoding.composerecipeapp.domain.model.Notification
import com.surivalcoding.composerecipeapp.domain.model.User
import com.surivalcoding.composerecipeapp.domain.repository.NotificationRepository
import com.surivalcoding.composerecipeapp.domain.repository.RecipeRepository
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationDataSource: NotificationDataSource,
    private val recipeRepository: RecipeRepository,
) : NotificationRepository {
    override suspend fun findByUser(user: User): List<Notification> {
        TODO("Not yet implemented")
    }

    override suspend fun markAsRead(notification: Notification): Boolean {
        /* no-op */
        return true
    }

    override suspend fun getSortedBySentDate(user: User): List<Notification> {
        val notifications =
            notificationDataSource.getNotification().getOrNull() ?: return emptyList()
        return notifications.getNotifications.map { it.toDomainModel() }
//            .filter { it.recipient == user }
            .sortedByDescending { it.sentAt }
    }

}
