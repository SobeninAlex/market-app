package com.example.cart.presentation

import com.example.cart.CartEvent
import com.example.resources.R
import com.example.cart.domain.CartRepository
import com.example.utils.domain.Cart
import com.example.utils.helper.ResultWrapper
import com.example.utils.presentation.BaseViewModel
import kotlinx.collections.immutable.toImmutableList
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

        is CartEvent.OnMinusClick -> {
            decrementQuantity(event.cart)
        }

        is CartEvent.OnPlusClick -> {
            incrementQuantity(event.cart)
        }

        is CartEvent.OnRemoveClick -> {
            removeCartItem(event.cart)
        }
    }

    private fun refresh() {
        _uiState.update { state -> state.copy(refresh = true) }
        getCart()
    }

    private fun getCart(loading: Boolean? = null) {
        viewModelScope.launch {
            loading?.let { _uiState.update { state -> state.copy(loading = true) } }
            delay(2500) //todo: test
            runCatching { cartRepository.getCart() }
                .onSuccess { result ->
                    when (result) {
                        is ResultWrapper.Failure -> {
                            _uiState.update { state ->
                                state.copy(
                                    loading = false,
                                    refresh = false
                                )
                            }
                            showSnackbar(resources.getString(R.string.core_common_error_message))
                        }

                        is ResultWrapper.Success -> {
                            _uiState.update { state ->
                                state.copy(
                                    loading = false,
                                    refresh = false,
                                    carts = result.value.carts.toImmutableList()
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

    private fun incrementQuantity(cart: Cart) {
        updateQuantity(cart.copy(quantity = cart.quantity + 1))
    }

    private fun decrementQuantity(cart: Cart) {
        updateQuantity(cart.copy(quantity = cart.quantity - 1))
    }

    private fun removeCartItem(cart: Cart) {
        //todo
    }

    private fun updateQuantity(cart: Cart) {
        updateQuantityCart(cart) { it.copy(changeQuantityProcess = true) }

        viewModelScope.launch {
            runCatching { cartRepository.updateQuantity(cart) }
                .onSuccess { result ->
                    when (result) {
                        is ResultWrapper.Failure -> {
                            showSnackbar(resources.getString(R.string.core_common_error_message))
                        }

                        is ResultWrapper.Success -> {
                            val updatedCart = result.value.carts.find { it.id == cart.id }
                            updatedCart?.let { item ->
                                updateQuantityCart(item) {
                                    it.copy(
                                        quantity = item.quantity,
                                        changeQuantityProcess = false
                                    )
                                }
                            } ?: showSnackbar(resources.getString(R.string.not_found_err_msg))
                        }
                    }
                }
                .onFailure { error ->
                    showSnackbar(error.message.toString())
                }
        }
    }

    private fun updateQuantityCart(
        cart: Cart,
        update: (Cart) -> Cart
    ) = _uiState.update { state ->
        val carts = state.carts.map {
            if (cart.id == it.id) {
                update(it)
            } else it
        }
        state.copy(carts = carts)
    }

}
