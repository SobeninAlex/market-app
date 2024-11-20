package com.example.utils.helper

sealed class NetworkResultWrapper<out T> {
    data class Success<out T>(val data: T) : NetworkResultWrapper<T>()
    data class Failure(val exception: Exception) : NetworkResultWrapper<Nothing>()
}

