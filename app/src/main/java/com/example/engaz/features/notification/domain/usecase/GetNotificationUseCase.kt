package com.example.engaz.features.notification.domain.usecase

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.notification.data.entities.get_notification.GetNotificationResponse
import com.example.engaz.features.notification.domain.repo.NotificationRepo
import javax.inject.Inject


class GetNotificationUseCase @Inject constructor(
    val repo: NotificationRepo,
    ) {

    suspend operator fun invoke(
        id: Int,
        token: String,
        context: Context
    ): Resource<GetNotificationResponse> {

        return repo.getNotification(
            id = id,
            token = token,
            context = context,
        )


    }

}