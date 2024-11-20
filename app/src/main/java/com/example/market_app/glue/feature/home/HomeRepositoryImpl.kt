package com.example.market_app.glue.feature.home

import com.example.data.network.NetworkService
import com.example.domain.Category
import com.example.domain.Product
import com.example.home.domain.HomeRepository
import com.example.utils.helper.NetworkResultWrapper

class HomeRepositoryImpl(
    private val networkService: NetworkService
) : HomeRepository {

    override suspend fun getCategories(): NetworkResultWrapper<List<Category>> {
        return networkService.getCategories()
    }

    override suspend fun getProducts(category: Int?): NetworkResultWrapper<List<Product>> {
        return networkService.getProducts(category)
    }
}