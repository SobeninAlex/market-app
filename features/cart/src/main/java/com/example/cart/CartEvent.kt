package com.example.cart

sealed interface CartEvent {

    data object Refresh : CartEvent

}