package com.example.cart.presentation

import com.example.cart.CartEvent
import com.example.resources.R
import com.example.cart.domain.CartRepository
import com.example.utils.helper.ResultWrapper
import com.example.utils.presentation.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(CartScreenUiState.DEFAULT)
    val uiState = _uiState.asStateFlow()

    init {
        getCart(loading = true)
    }

    fun onEvent(event: CartEvent) = when (event) {
        is CartEvent.Refresh -> {
            refresh()
        }
    }

    private fun refresh() {
        _uiState.update { state -> state.copy(refresh = true) }
        getCart()
    }

    private fun getCart(loading: Boolean? = null) {
        viewModelScope.launch {
            loading?.let { _uiState.update { state -> state.copy(loading = true) } }
            delay(5000)
            runCatching { cartRepository.getCart() }
                .onSuccess { result ->
                    when (result) {
                        is ResultWrapper.Failure -> {
                            _uiState.update { state -> state.copy(loading = false, refresh = false) }
                            showSnackbar(resources.getString(R.string.core_common_error_message))
                        }

                        is ResultWrapper.Success -> {
                            _uiState.update { state ->
                                state.copy(
                                    loading = false,
                                    refresh = false,
                                    carts = result.value.carts
                                )
                            }
                        }
                    }
                }
                .onFailure { error ->
                    _uiState.update { state -> state.copy(loading = false, refresh = false) }
                    throw error
                }
        }
    }

}
