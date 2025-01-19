package com.surivalcoding.composerecipeapp.data.model

import kotlinx.datetime.Instant

data class Notification(
    val title: String,
    val contents: String,
    val sentAt: Instant,
    val recipient: User,
    val isRead: Boolean,
    val linkedPost: Post<*>,
)
