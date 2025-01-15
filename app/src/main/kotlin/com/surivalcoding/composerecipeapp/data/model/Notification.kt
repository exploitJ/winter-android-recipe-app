package com.surivalcoding.composerecipeapp.data.model

import kotlinx.datetime.LocalDateTime

data class Notification(
    val title: String,
    val contents: String,
    val sentAt: LocalDateTime,
    val recipient: User,
    val isRead: Boolean,
    val linkedPost: Post<*>,
)
