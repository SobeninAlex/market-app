package com.example.domain.network

import com.example.domain.entity.CategoryList
import com.example.domain.entity.Product
import com.example.domain.entity.ProductList

interface NetworkService {

    suspend fun getProducts(category: Int?): ResultWrapper<ProductList>
    suspend fun getCategories(): ResultWrapper<CategoryList>

}

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Failure(val exception: Exception) : ResultWrapper<Nothing>()
}