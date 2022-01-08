package com.fabledt5.noustecompose.domain.model

sealed class AppState<out T> {
    object Idle : AppState<Nothing>()
    object Loading : AppState<Nothing>()
    class Success<T>(val data: T) : AppState<T>()
    class Error<T>(val message: Throwable) : AppState<T>()
}