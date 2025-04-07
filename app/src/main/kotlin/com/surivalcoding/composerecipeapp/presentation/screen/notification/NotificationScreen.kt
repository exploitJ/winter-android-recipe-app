package com.surivalcoding.composerecipeapp.presentation.screen.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.surivalcoding.composerecipeapp.presentation.component.AppSegmentedButtonRow
import com.surivalcoding.composerecipeapp.presentation.shared.AppTextStyles
import com.surivalcoding.composerecipeapp.presentation.shared.Loading
import com.surivalcoding.composerecipeapp.presentation.shared.theme.AppColors
import kotlin.time.DurationUnit
import kotlin.time.toDuration


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(bottomPadding: Dp, notificationUiState: NotificationUiState) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                expandedHeight = 47.dp,
                title = {
                    Text(
                        text = "Notifications",
                        style = AppTextStyles.mediumTextBold,
                        color = AppColors.font,
                    )
                },


                )
        }
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Column {
            val filter = rememberSaveable { mutableIntStateOf(0) }

            AppSegmentedButtonRow(
                labels = listOf("All", "Read", "Unread"),
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                onValueChange = { filter.intValue = it },
                scrollPadding = PaddingValues(vertical = 10.dp, horizontal = 30.dp)
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .verticalScroll(scrollState)
            ) {

                when (notificationUiState) {
                    NotificationUiState.Loading -> Loading()
                    is NotificationUiState.Success -> {
                        val notifications = notificationUiState.all.filter {
                            when (filter.intValue) {
                                1 -> it.isRead
                                2 -> !it.isRead
                                else -> true
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            notifications.filter { it.durationSince.inWholeDays == 0L }.let {
                                if (it.isNotEmpty()) {
                                    NotificationGroup(
                                        title = "Today",
                                        data = it
                                    )
                                }
                            }
                            notifications.filter { it.durationSince.inWholeDays == 1L }.let {
                                if (it.isNotEmpty()) {
                                    NotificationGroup(
                                        title = "Yesterday",
                                        data = it
                                    )
                                }
                            }
                            notifications.filter { it.durationSince.inWholeDays > 1L }.let {
                                if (it.isNotEmpty()) {
                                    NotificationGroup(
                                        title = "Past notifications",
                                        data = it
                                    )
                                }
                            }

                        }

                    }
                }
                Spacer(modifier = Modifier.padding(bottom = bottomPadding))
            }
        }
    }

}

@Composable
fun NotificationGroup(
    modifier: Modifier = Modifier,
    title: String,
    data: List<NotificationCardData>,
) {

    Column(
        modifier = modifier.wrapContentSize(),
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.CenterHorizontally),
            text = title,
            style = AppTextStyles.smallerTextBold,
            color = AppColors.black
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            data.forEach { item ->
                NotificationCard(
                    title = item.title, content = item.content, isRead = item.isRead,
                    timeElapsedText = item.timeElapsedText,
                    icon = {
                        when (item.type) {
                            NotificationType.NEW_POST -> NotificationIcon(
                                item.isRead,
                                Icons.Default.Description
                            )

                            NotificationType.NEW_POST_FOLLOWER -> NotificationIcon(
                                item.isRead,
                                Icons.Default.Stars
                            )
                        }
                    },
                    onClick = {}
                )
            }
        }

    }
}

@Composable
private fun NotificationIcon(isRead: Boolean, image: ImageVector) {
    Box(
        modifier = Modifier
            .background(
                color = AppColors.secondary20,
                shape = RoundedCornerShape(10.dp)
            )
            .size(28.dp),
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(6.dp),
            imageVector = image,
            contentDescription = null,
            tint = AppColors.secondary100,
        )
        if (!isRead) {
            Box(
                modifier = Modifier
                    .padding(1.dp)
                    .align(Alignment.TopEnd)
                    .size(5.dp)
                    .background(color = AppColors.secondary100, shape = CircleShape)
                    .border(width = (0.5).dp, color = Color.White, shape = CircleShape)
            )
        }
    }
}

@Composable
fun ColumnScope.NotificationCard(
    modifier: Modifier = Modifier,
    title: String, content: String, isRead: Boolean, timeElapsedText: String,
    icon: @Composable () -> Unit = {},
    onClick: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = AppColors.gray4,
        ),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(title, style = AppTextStyles.smallerTextBold, color = AppColors.font)
                Text(
                    content,
                    minLines = 2,
                    maxLines = 2,
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.gray3,
                )
                Text(
                    timeElapsedText,
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.gray3
                )
            }
            Spacer(Modifier.padding(horizontal = 10.dp))
            icon()
        }

    }
}

@Preview(backgroundColor = 0xfff, showBackground = true)
@Composable
private fun NotificationGroupPreview() {
    val notifications = (0..3).map {
        NotificationCardData(
            "title",
            "content",
            durationSince = it.toDuration(DurationUnit.DAYS),
            "time",
            it % 2 == 0,
            if (it % 2 == 1) NotificationType.NEW_POST else NotificationType.NEW_POST_FOLLOWER
        )
    }
    NotificationScreen(0.dp, NotificationUiState.Success(notifications))
}