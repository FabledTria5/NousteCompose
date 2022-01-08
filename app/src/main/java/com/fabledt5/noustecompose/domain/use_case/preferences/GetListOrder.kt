package com.fabledt5.noustecompose.domain.use_case.preferences

import com.fabledt5.noustecompose.domain.model.ListsOrder
import com.fabledt5.noustecompose.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetListOrder @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    operator fun invoke() = dataStoreRepository.listOrder.map {
        if (it.isEmpty()) ListsOrder.NORMAL
        else ListsOrder.valueOf(it)
    }
}