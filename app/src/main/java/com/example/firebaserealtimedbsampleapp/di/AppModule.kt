package com.example.firebaserealtimedbsampleapp.di

import com.example.firebaserealtimedbsampleapp.data.repository.DevicesRepositoryImp
import com.example.firebaserealtimedbsampleapp.data.source.RemoteDataSource
import com.example.firebaserealtimedbsampleapp.domain.repository.DevicesRepository
import com.example.firebaserealtimedbsampleapp.domain.usecases.GetDeviceStatusUseCase
import com.example.firebaserealtimedbsampleapp.domain.usecases.UpdateDeviceStatusUseCase
import com.google.firebase.database.DatabaseReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        @IODispatcher coroutineDispatcher: CoroutineDispatcher,
        databaseReference: DatabaseReference
    ): RemoteDataSource {
        return RemoteDataSource(
            coroutineDispatcher = coroutineDispatcher,
            databaseReference = databaseReference
        )
    }

    @Provides
    @Singleton
    fun provideDevicesRepository(
        remoteDataSource: RemoteDataSource
    ): DevicesRepository {
        return DevicesRepositoryImp(remoteDataSource)
    }

//    @Provides
//    fun provideGetDeviceStatusUseCase(
//        devicesRepository: DevicesRepository
//    ): GetDeviceStatusUseCase {
//        return GetDeviceStatusUseCase(devicesRepository)
//    }

    @Provides
    fun provideUpdateDeviceStatusUseCase(
       devicesRepository: DevicesRepository
    ): UpdateDeviceStatusUseCase {
        return UpdateDeviceStatusUseCase(devicesRepository)
    }
}