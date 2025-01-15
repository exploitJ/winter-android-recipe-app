package com.surivalcoding.composerecipeapp.data.repository

import com.surivalcoding.composerecipeapp.data.model.Notification
import com.surivalcoding.composerecipeapp.data.model.User

interface NotificationRepository {
    fun findByUser(user: User): List<Notification>
    fun markAsRead(notification: Notification): Boolean
    fun getSortedBySentDate(user: User): List<Notification>
}