package com.example.firebaserealtimedbsampleapp.domain.repository

import com.example.firebaserealtimedbsampleapp.resource.SimpleResource
import kotlinx.coroutines.flow.Flow


interface DevicesRepository {

    fun getDeviceStatus(): Flow<Boolean>

    suspend fun updateDeviceStatus(status: Boolean): SimpleResource
}