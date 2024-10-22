package com.example.market_app.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Product
import com.example.domain.network.ResultWrapper
import com.example.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Initial)
    val uiState = _uiState.asStateFlow()

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            _uiState.value = HomeScreenUiState.Loading
            runCatching {
                val featured = getProducts("electronics")
                val popularProducts = getProducts("jewelery")
                Pair(first = featured, second = popularProducts)
            }.onSuccess { result ->
                if (result.first.isEmpty() || result.second.isEmpty()) {
                    _uiState.value = HomeScreenUiState.Failure(message = "Failed to load products")
                    return@launch
                }
                _uiState.value = HomeScreenUiState.Success(featured = result.first, popularProducts = result.second)
            }.onFailure { error ->
                _uiState.value = HomeScreenUiState.Failure(message = error.message.toString())
            }
        }
    }

    private suspend fun getProducts(category: String?): List<Product> {
        getProductUseCase(category).let { result ->
            when (result) {
                is ResultWrapper.Failure -> {
                    return emptyList()
                }
                is ResultWrapper.Success -> {
                    return result.value
                }
            }
        }
    }
}

sealed interface HomeScreenUiState {
    data object Initial : HomeScreenUiState
    data object Loading : HomeScreenUiState
    data class Success(val featured: List<Product>, val popularProducts: List<Product>) : HomeScreenUiState
    data class Failure(val message: String) : HomeScreenUiState
}