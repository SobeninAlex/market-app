package com.example.domain.network

import com.example.domain.entity.CartList
import com.example.domain.entity.CategoryList
import com.example.domain.entity.ProductList
import com.example.domain.entity.request.AddCartRequest

interface NetworkService {

    suspend fun getProducts(category: Int?): ResultWrapper<ProductList>

    suspend fun getCategories(): ResultWrapper<CategoryList>

    suspend fun addProductToCart(request: AddCartRequest) : ResultWrapper<CartList>

    suspend fun getCart(): ResultWrapper<CartList>

}

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Failure(val exception: Exception) : ResultWrapper<Nothing>()
}