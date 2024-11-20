package com.example.cart.presentation.cart

sealed interface CartEvent {

    data object Refresh : CartEvent

    data class OnPlusClick(val cart: com.example.domain.Cart): CartEvent

    data class OnMinusClick(val cart: com.example.domain.Cart) : CartEvent

    data class OnRemoveClick(val cart: com.example.domain.Cart) : CartEvent

}