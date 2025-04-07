package com.surivalcoding.composerecipeapp.presentation.screen.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surivalcoding.composerecipeapp.data.util.toUiModel
import com.surivalcoding.composerecipeapp.domain.model.Notification
import com.surivalcoding.composerecipeapp.domain.model.User
import com.surivalcoding.composerecipeapp.domain.repository.NotificationRepository
import com.surivalcoding.composerecipeapp.domain.repository.UserInfoRepository
import com.surivalcoding.composerecipeapp.presentation.shared.PreviewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
    private val notificationRepository: NotificationRepository,
) : ViewModel() {

    private val _me = MutableStateFlow<User>(PreviewData.user)
    val me = _me.asStateFlow()

    private val _notificationUiState =
        MutableStateFlow<NotificationUiState>(NotificationUiState.Loading)
    val notificationUiState = _notificationUiState.asStateFlow()

    init {
        viewModelScope.launch {
            _me.update {
                userInfoRepository.getCurrentUser()
            }
        }
        fetchNotifications()
    }

    private suspend fun getAllNotifications() = notificationRepository.getSortedBySentDate(me.value)

    fun fetchNotifications() {
        viewModelScope.launch {
            _notificationUiState.update {
                val notifications = getAllNotifications().map(Notification::toUiModel)
                NotificationUiState.Success(notifications)
            }
        }
    }

    fun markAsRead(notification: Notification) {
        viewModelScope.launch {
            _notificationUiState.update { state ->
                state as NotificationUiState.Success
                notificationRepository.markAsRead(notification)
                NotificationUiState.Success(state.all)
            }
        }
    }
}

sealed interface NotificationUiState {
    data object Loading : NotificationUiState
    data class Success(
        val all: List<NotificationCardData>,
    ) : NotificationUiState
}

data class NotificationCardData(
    val title: String,
    val content: String,
    val durationSince: Duration,
    val timeElapsedText: String,
    val isRead: Boolean,
    val type: NotificationType,
)

enum class NotificationType {
    NEW_POST, NEW_POST_FOLLOWER
}