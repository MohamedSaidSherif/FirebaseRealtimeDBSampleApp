package com.example.firebaserealtimedbsampleapp.domain.usecases

import com.example.firebaserealtimedbsampleapp.domain.repository.DevicesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDeviceStatusUseCase @Inject constructor(
    private val devicesRepository: DevicesRepository
){
    operator fun invoke(): Flow<Boolean> {
        return devicesRepository.getDeviceStatus()
    }
}