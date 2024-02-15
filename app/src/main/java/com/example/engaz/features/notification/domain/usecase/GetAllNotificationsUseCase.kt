package com.example.engaz.features.notification.domain.usecase

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.notification.data.entities.get_all_notification.GetAllNotificationsResponse
import com.example.engaz.features.notification.domain.repo.NotificationRepo
import javax.inject.Inject


class GetAllNotificationsUseCase @Inject constructor(
    val repo: NotificationRepo,
    ) {

    suspend operator fun invoke(
        token: String,
        context: Context
    ): Resource<GetAllNotificationsResponse> {

        return repo.getAllNotifications(
            token = token,
            context = context,
        )


    }

}