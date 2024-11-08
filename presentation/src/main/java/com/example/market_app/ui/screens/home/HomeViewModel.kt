package com.example.market_app.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Product
import com.example.domain.network.ResultWrapper
import com.example.domain.usecase.GetCategoriesUseCase
import com.example.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getProductUseCase: GetProductUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
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
                val featured = getProducts(1)
                val popularProducts = getProducts(2)
                val categories = getCategories()
                Triple(first = featured, second = popularProducts, third = categories)
            }.onSuccess { result ->
                if (result.first.isEmpty() && result.second.isEmpty() && result.third.isEmpty()) {
                    _uiState.value = HomeScreenUiState.Failure(message = "Failed to load products")
                    return@launch
                }
                _uiState.value = HomeScreenUiState.Success(
                    featured = result.first,
                    popularProducts = result.second,
                    categories = result.third
                )
            }.onFailure { error ->
                _uiState.value = HomeScreenUiState.Failure(message = error.message.toString())
            }
        }
    }

    private suspend fun getProducts(category: Int?): List<Product> {
        getProductUseCase(category).let { result ->
            when (result) {
                is ResultWrapper.Failure -> {
                    return emptyList()
                }
                is ResultWrapper.Success -> {
                    return result.value.products
                }
            }
        }
    }

    private suspend fun getCategories(): List<String> {
        getCategoriesUseCase().let { result ->
            when (result) {
                is ResultWrapper.Failure -> {
                    return emptyList()
                }

                is ResultWrapper.Success -> {
                    return result.value.categories.map { it.title }
                }
            }
        }
    }
}

sealed interface HomeScreenUiState {
    data object Initial : HomeScreenUiState
    data object Loading : HomeScreenUiState
    data class Success(
        val featured: List<Product>,
        val popularProducts: List<Product>,
        val categories: List<String>
    ) : HomeScreenUiState
    data class Failure(val message: String) : HomeScreenUiState
}