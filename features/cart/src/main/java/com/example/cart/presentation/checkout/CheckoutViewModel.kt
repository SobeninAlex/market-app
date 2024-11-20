package com.example.cart.presentation.checkout

import com.example.cart.domain.CartRepository
import com.example.resources.R
import com.example.utils.helper.NetworkResultWrapper
import com.example.utils.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CheckoutViewModel(
    private val repository: CartRepository
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(CheckoutUiState.DEFAULT)
    val uiState = _uiState.asStateFlow()

    init {
        getCartSummary(loading = true)
    }

    fun onEvent(event: CheckoutEvent) = when (event) {
        is CheckoutEvent.Refresh -> {
            getCartSummary(refresh = true)
        }
    }

    private fun getCartSummary(loading: Boolean = false, refresh: Boolean = false) {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(loading = loading, refresh = refresh)
            }

            runCatching { repository.getCartSummary(1) }
                .onSuccess { result ->
                    when (result) {
                        is NetworkResultWrapper.Failure -> {
                            _uiState.update { state ->
                                state.copy(loading = false, refresh = false)
                            }
                            showSnackbar(message = result.exception.message ?: resources.getString(R.string.core_common_error_message))
                        }

                        is NetworkResultWrapper.Success -> {
                            _uiState.update { state ->
                                state.copy(
                                    loading = false,
                                    refresh = false,
                                    summary = result.data
                                )
                            }
                        }
                    }
                }
                .onFailure { error ->
                    showSnackbar(message = error.message.toString())
                }
        }
    }

}