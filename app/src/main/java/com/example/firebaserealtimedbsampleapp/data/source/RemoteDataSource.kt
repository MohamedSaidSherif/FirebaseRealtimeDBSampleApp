package com.example.firebaserealtimedbsampleapp.data.source

import com.example.firebaserealtimedbsampleapp.di.IODispatcher
import com.example.firebaserealtimedbsampleapp.resource.Resource
import com.example.firebaserealtimedbsampleapp.resource.SimpleResource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    @IODispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val databaseReference: DatabaseReference
) {

    companion object {
        private const val DEVICE_CONNECTION_STATUS_KEY = "ConnectionStatus"
    }

    fun getDeviceStatus(): Flow<Boolean> = callbackFlow {
        val boolRef = databaseReference.child(DEVICE_CONNECTION_STATUS_KEY)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
               snapshot.getValue(Boolean::class.java)?.let {
                    trySend(it)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        boolRef.addValueEventListener(listener)
        awaitClose { boolRef.removeEventListener(listener) }
    }

    suspend fun updateDeviceStatus(status: Boolean): SimpleResource {
        return withContext(coroutineDispatcher) {
            databaseReference.child(DEVICE_CONNECTION_STATUS_KEY).setValue(status).await()
            Resource.Success(Unit)
        }
    }
}