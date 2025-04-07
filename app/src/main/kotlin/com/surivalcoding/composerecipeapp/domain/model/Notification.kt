package com.surivalcoding.composerecipeapp.domain.model

import kotlinx.datetime.Instant
import java.util.UUID

data class Notification(
    val title: String,
    val contents: String,
    val sentAt: Instant,
    val recipient: User,
    val isRead: Boolean,
    val linkedPost: Post<*>,
)

typealias NotificationId = UUID