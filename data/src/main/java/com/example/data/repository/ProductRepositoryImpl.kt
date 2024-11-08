package com.example.data.repository

import com.example.domain.entity.Product
import com.example.domain.entity.ProductList
import com.example.domain.network.NetworkService
import com.example.domain.network.ResultWrapper
import com.example.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val networkService: NetworkService
) : ProductRepository {

    override suspend fun getProducts(category: Int?): ResultWrapper<ProductList> {
        return networkService.getProducts(category)
    }
}