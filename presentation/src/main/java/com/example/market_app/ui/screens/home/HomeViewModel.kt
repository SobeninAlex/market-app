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
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            _uiState.value = HomeScreenUiState.Loading
            runCatching {
                getProductUseCase()
            }.onSuccess { result ->
                when (result) {
                    is ResultWrapper.Failure -> {
                        _uiState.value = HomeScreenUiState.Failure(result.exception.message.toString())
                    }
                    is ResultWrapper.Success -> {
                        _uiState.value = HomeScreenUiState.Success(result.value)
                    }
                }
            }.onFailure {
                _uiState.value = HomeScreenUiState.Failure(it.message.toString())
            }
        }
    }

}

sealed interface HomeScreenUiState {
    data object Initial : HomeScreenUiState
    data object Loading : HomeScreenUiState
    data class Success(val products: List<Product>) : HomeScreenUiState
    data class Failure(val message: String) : HomeScreenUiState
}