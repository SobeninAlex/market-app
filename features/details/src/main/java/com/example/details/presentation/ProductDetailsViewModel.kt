package com.example.details.presentation

import com.example.common.Core
import com.example.details.ProductDetailsEvent
import com.example.details.domain.DetailRepository
import com.example.resources.R
import com.example.utils.domain.Product
import com.example.utils.event.SnackbarAction
import com.example.utils.helper.ResultWrapper
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
                detailRepository.addProductToCart(product.toAddCartRequest(quantity, 1))
            }.onSuccess { result ->
                when (result) {
                    is ResultWrapper.Failure -> {
                        _state.value = ProductDetailsScreenUiState.Failure
                        showSnackbar(message = result.exception.message ?: "Something went wrong!")
                    }

                    is ResultWrapper.Success -> {
                        _state.value = ProductDetailsScreenUiState.Success
                        showSnackbar(
                            //message = "Product ${product.title} added to cart",
                            message = resources.getString(R.string.view_all),
                            action = SnackbarAction(
                                buttonName = "Back",
                                action = {
                                    goBack()
                                }
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