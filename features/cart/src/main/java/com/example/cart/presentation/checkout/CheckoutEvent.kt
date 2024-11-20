package com.example.cart.presentation.checkout

sealed interface CheckoutEvent {

    data object Refresh : CheckoutEvent

}