package com.fabledt5.noustecompose.domain.use_case.preferences

import javax.inject.Inject

data class PreferencesActionsWrapper @Inject constructor(
    val getListOrder: GetListOrder,
    val setListOrder: SetListOrder
)