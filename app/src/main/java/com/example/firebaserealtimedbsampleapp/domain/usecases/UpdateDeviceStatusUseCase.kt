package com.example.firebaserealtimedbsampleapp.domain.usecases

import com.example.firebaserealtimedbsampleapp.resource.SimpleResource
import com.example.firebaserealtimedbsampleapp.domain.repository.DevicesRepository
import javax.inject.Inject

class UpdateDeviceStatusUseCase @Inject constructor(
    private val devicesRepository: DevicesRepository
) {
    suspend operator fun invoke(status: Boolean): SimpleResource {
        return devicesRepository.updateDeviceStatus(status)
    }
}