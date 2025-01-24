package com.surivalcoding.composerecipeapp.data.datasource

import com.surivalcoding.composerecipeapp.data.dto.NotificationsResponse

interface NotificationDataSource {
    suspend fun getNotification(): Result<NotificationsResponse>
}