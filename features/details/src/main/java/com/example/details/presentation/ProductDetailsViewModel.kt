package com.example.details.presentation

import com.example.details.ProductDetailsEvent
import com.example.details.domain.DetailRepository
import com.example.resources.R
import com.example.domain.Product
import com.example.utils.event.SnackbarAction
import com.example.utils.helper.NetworkResultWrapper
import com.example.utils.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val detailRepository: DetailRepository,
) : BaseViewModel() {

    private val _state =
        MutableStateFlow<ProductDetailsScreenUiState>(ProductDetailsScreenUiState.Initial)
    val state = _state.asStateFlow()

    fun onEvent(event: ProductDetailsEvent) {
        when (event) {
            is ProductDetailsEvent.AddProductToCart -> {
                addProductToCart(event.product)
            }
        }
    }

    private fun addProductToCart(product: Product, quantity: Int = 1) {
        viewModelScope.launch {
            _state.value = ProductDetailsScreenUiState.Loading("Adding to cart...")
            runCatching {
                detailRepository.addProductToCart(productId = product.id, quantity = quantity)
            }.onSuccess { result ->
                when (result) {
                    is NetworkResultWrapper.Failure -> {
                        _state.value = ProductDetailsScreenUiState.Failure
                        showSnackbar(message = result.exception.message ?: "Something went wrong!")
                    }

                    is NetworkResultWrapper.Success -> {
                        _state.value = ProductDetailsScreenUiState.Success
                        showSnackbar(
                            message = resources.getString(R.string.product_added_msg),
                            action = SnackbarAction(
                                buttonName = "Back",
                                action = { goBack() }
                            )
                        )
                    }
                }
            }.onFailure {
                _state.value = ProductDetailsScreenUiState.Failure
                showSnackbar(message = it.message ?: "Something went wrong!")
            }
        }
    }

}

sealed interface ProductDetailsScreenUiState {
    data object Initial : ProductDetailsScreenUiState
    data class Loading(val message: String) : ProductDetailsScreenUiState
    data object Success : ProductDetailsScreenUiState
    data object Failure : ProductDetailsScreenUiState
}