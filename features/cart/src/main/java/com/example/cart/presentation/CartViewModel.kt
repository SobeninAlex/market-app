package com.example.cart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cart.domain.CartRepository
import com.example.utils.domain.Cart
import com.example.utils.helper.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartScreenUiState.DEFAULT)
    val uiState = _uiState.asStateFlow()

    init {

    }

    private fun getCart() {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(loading = true) }
            runCatching { cartRepository.getCart() }
                .onSuccess { result ->
                    when (result) {
                        is ResultWrapper.Failure -> {
                            _uiState.update { state -> state.copy(loading = false) }
                        }

                        is ResultWrapper.Success -> {
                            _uiState.update { state ->
                                state.copy(
                                    loading = false,
                                    carts = result.value.carts
                                )
                            }
                        }
                    }
                }
                .onFailure {
                    _uiState.update { state -> state.copy(loading = false) }
                }
        }
    }

}

data class CartScreenUiState(
    val loading: Boolean,
    val carts: List<Cart>
) {

    companion object {
        val DEFAULT = CartScreenUiState(
            loading = false,
            carts = emptyList()
        )
    }

}