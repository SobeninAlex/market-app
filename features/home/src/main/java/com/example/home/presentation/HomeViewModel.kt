package com.example.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.HomeRepository
import com.example.utils.helper.NetworkResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeRepository
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

    private suspend fun getProducts(category: Int?): List<com.example.domain.Product> {
        homeRepository.getProducts(category).let { result ->
            when (result) {
                is NetworkResultWrapper.Failure -> {
                    return emptyList()
                }
                is NetworkResultWrapper.Success -> {
                    return result.data
                }
            }
        }
    }

    private suspend fun getCategories(): List<String> {
        homeRepository.getCategories().let { result ->
            when (result) {
                is NetworkResultWrapper.Failure -> {
                    return emptyList()
                }

                is NetworkResultWrapper.Success -> {
                    return result.data.map { it.title }
                }
            }
        }
    }
}

sealed interface HomeScreenUiState {
    data object Initial : HomeScreenUiState
    data object Loading : HomeScreenUiState
    data class Success(
        val featured: List<com.example.domain.Product>,
        val popularProducts: List<com.example.domain.Product>,
        val categories: List<String>
    ) : HomeScreenUiState
    data class Failure(val message: String) : HomeScreenUiState
}