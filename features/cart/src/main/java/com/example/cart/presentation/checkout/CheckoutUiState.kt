package com.example.cart.presentation.checkout

import androidx.compose.runtime.Immutable
import com.example.domain.Summary

@Immutable
data class CheckoutUiState(
    val loading: Boolean,
    val refresh: Boolean,
    val summary: Summary
) {

    companion object {
        val DEFAULT = CheckoutUiState(
            loading = false,
            refresh = false,
            summary = Summary.DEFAULT
        )

        val FAKE = CheckoutUiState(
            loading = false,
            refresh = false,
            summary = Summary.FAKE
        )
    }

}
