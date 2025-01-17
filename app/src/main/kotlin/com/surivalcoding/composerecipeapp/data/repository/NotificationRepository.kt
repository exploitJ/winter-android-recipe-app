package com.surivalcoding.composerecipeapp.data.repository

import com.surivalcoding.composerecipeapp.data.model.Notification
import com.surivalcoding.composerecipeapp.data.model.User

interface NotificationRepository {
    suspend fun findByUser(user: User): List<Notification>
    suspend fun markAsRead(notification: Notification): Boolean
    suspend fun getSortedBySentDate(user: User): List<Notification>
}