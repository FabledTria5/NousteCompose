package com.fabledt5.noustecompose.domain.model

enum class ListsOrder {
    NORMAL {
        override fun next() = REVERSED
    },
    REVERSED {
        override fun next() = NORMAL
    };

    abstract fun next(): ListsOrder
}