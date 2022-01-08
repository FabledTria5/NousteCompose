package com.fabledt5.noustecompose.presentation.utils

enum class Action {
    ADD,
    DELETE,
    UNDO,
    NO_ACTION
}

fun String?.toAction() = this?.let { Action.valueOf(it) } ?: Action.NO_ACTION