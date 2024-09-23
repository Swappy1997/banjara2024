package com.example.banjaraworld.common

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe(): Flow<Status>
    data class Status(val isConnected:Boolean=true)
}