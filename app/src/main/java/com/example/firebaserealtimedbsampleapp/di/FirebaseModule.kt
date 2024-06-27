package com.example.firebaserealtimedbsampleapp.di


import com.google.firebase.Firebase
import com.google.firebase.database.database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {


    @Singleton
    @Provides
    fun provideFirebaseDatabaseReference() = Firebase.database.reference

}