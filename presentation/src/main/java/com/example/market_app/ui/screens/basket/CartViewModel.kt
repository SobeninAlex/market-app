package com.example.market_app.ui.screens.basket

import android.widget.Toast
import androidx.compose.material3.SnackbarDefaults
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Cart
import com.example.domain.entity.CartList
import com.example.domain.network.ResultWrapper
import com.example.domain.usecase.GetCartUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.dsl.koinApplication

class CartViewModel(
    private val getCartUseCase: GetCartUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartScreenUiState.DEFAULT)
    val uiState = _uiState.asStateFlow()

    init {

    }

    private fun getCart() {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(loading = true) }
            runCatching { getCartUseCase() }
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