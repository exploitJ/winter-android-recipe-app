package com.surivalcoding.composerecipeapp.domain.repository

import com.surivalcoding.composerecipeapp.domain.model.Notification
import com.surivalcoding.composerecipeapp.domain.model.User

interface NotificationRepository {
    suspend fun findByUser(user: User): List<Notification>
    suspend fun markAsRead(notification: Notification): Boolean
    suspend fun getSortedBySentDate(user: User): List<Notification>
}