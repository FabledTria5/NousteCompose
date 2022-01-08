package com.fabledt5.noustecompose.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    val listOrder: Flow<String>
    suspend fun persistListOrders(listOrder: String)
}