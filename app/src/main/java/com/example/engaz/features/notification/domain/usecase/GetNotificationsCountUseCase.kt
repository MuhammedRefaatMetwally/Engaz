package com.example.engaz.features.notification.domain.usecase

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.notification.data.entities.get_notifications_count.GetNotificationsCountResponse
import com.example.engaz.features.notification.domain.repo.NotificationRepo
import javax.inject.Inject


class GetNotificationsCountUseCase @Inject constructor(
    val repo: NotificationRepo,
    ) {

    suspend operator fun invoke(
        token: String,
        context: Context
    ): Resource<GetNotificationsCountResponse> {

        return repo.getNotificationCount(
            token = token,
            context = context,
        )


    }

}