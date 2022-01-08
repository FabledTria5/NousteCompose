package com.fabledt5.noustecompose.domain.use_case.preferences

import com.fabledt5.noustecompose.domain.model.ListsOrder
import com.fabledt5.noustecompose.domain.repository.DataStoreRepository
import javax.inject.Inject

class SetListOrder @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(order: ListsOrder) {
        dataStoreRepository.persistListOrders(order.next().name)
    }
}