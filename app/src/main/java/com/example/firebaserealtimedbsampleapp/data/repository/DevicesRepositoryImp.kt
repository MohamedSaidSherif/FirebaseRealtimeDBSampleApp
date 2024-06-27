package com.example.firebaserealtimedbsampleapp.data.repository

import com.example.firebaserealtimedbsampleapp.data.source.RemoteDataSource
import com.example.firebaserealtimedbsampleapp.domain.repository.DevicesRepository
import com.example.firebaserealtimedbsampleapp.resource.SimpleResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DevicesRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): DevicesRepository {

    override fun getDeviceStatus(): Flow<Boolean> {
        return remoteDataSource.getDeviceStatus()
    }

    override suspend fun updateDeviceStatus(status: Boolean): SimpleResource {
        return remoteDataSource.updateDeviceStatus(status)
    }
}