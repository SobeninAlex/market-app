package com.example.cart

import com.example.utils.domain.Cart

sealed interface CartEvent {

    data object Refresh : CartEvent

    data class OnPlusClick(val cart: Cart): CartEvent

    data class OnMinusClick(val cart: Cart) : CartEvent

    data class OnRemoveClick(val cart: Cart) : CartEvent

}