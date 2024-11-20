package com.example.cart.presentation.cart

import com.example.resources.R
import com.example.cart.domain.CartRepository
import com.example.domain.Cart
import com.example.utils.helper.NetworkResultWrapper
import com.example.utils.presentation.BaseViewModel
import com.example.utils.presentation.mergeWith
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository
) : BaseViewModel() {

    private var refresher: Boolean? = null
    private var loader: Boolean? = null

    private val _uiState = MutableStateFlow(CartUiState.DEFAULT)

    private val cartFlow = cartRepository.cart
        .map { result ->
            when (result) {
                is NetworkResultWrapper.Failure -> {
                    _uiState.value.copy(
                        loading = false,
                        refresh = false
                    )
                }

                is NetworkResultWrapper.Success -> {
                    _uiState.value.copy(
                        loading = false,
                        refresh = false,
                        carts = result.data
                    )
                }
            }
        }
        .catch { error ->
            _uiState.update { state ->
                state.copy(
                    loading = false,
                    refresh = false
                )
            }
            showSnackbar(error.message.toString())
        }
        .onStart {
            refresh(loading = loader, refresh = refresher)
        }
        .onEach { result ->
            _uiState.value = result
            loader = null
            refresher = null
        }

    val uiState = _uiState.mergeWith(cartFlow)

    init {
        loader = true
        refresher = false
        getCart()
    }

    fun onEvent(event: CartEvent) = when (event) {
        is CartEvent.Refresh -> {
            loader = false
            refresher = true
            getCart()
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

    private fun refresh(loading: Boolean?, refresh: Boolean?) {
        _uiState.update { state ->
            state.copy(loading = loading ?: true, refresh = refresh ?: false)
        }
    }

    private fun getCart() {
        viewModelScope.launch {
            cartFlow
                .stateIn(viewModelScope)
                .collect()
        }
    }

    private fun incrementQuantity(cart: Cart) {
        updateQuantity(cart.copy(quantity = cart.quantity + 1))
    }

    private fun decrementQuantity(cart: Cart) {
        updateQuantity(cart.copy(quantity = cart.quantity - 1))
    }

    private fun removeCartItem(cart: Cart) {
        updateQuantityCart(cart) { it.copy(deleteProcess = true) }

        viewModelScope.launch {
            runCatching { cartRepository.removeItem(cartId = cart.id, userId = 1) }
                .onSuccess { result ->
                    when (result) {
                        is NetworkResultWrapper.Failure -> {
                            showSnackbar(resources.getString(R.string.core_common_error_message))
                        }

                        is NetworkResultWrapper.Success -> {
                            _uiState.update { state ->
                                state.copy(
                                    carts = result.data
                                )
                            }
                        }
                    }
                }
                .onFailure { error ->
                    showSnackbar(error.message.toString())
                }
        }
    }

    private fun updateQuantity(cart: Cart) {
        updateQuantityCart(cart) { it.copy(changeQuantityProcess = true) }

        viewModelScope.launch {
            runCatching { cartRepository.updateQuantity(cart) }
                .onSuccess { result ->
                    when (result) {
                        is NetworkResultWrapper.Failure -> {
                            showSnackbar(resources.getString(R.string.core_common_error_message))
                        }

                        is NetworkResultWrapper.Success -> {
                            val updatedCart = result.data.find { it.id == cart.id }
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
