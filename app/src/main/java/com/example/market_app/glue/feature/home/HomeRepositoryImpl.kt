package com.example.market_app.glue.feature.home

import com.example.data.network.NetworkService
import com.example.utils.domain.CategoryList
import com.example.utils.domain.ProductList
import com.example.home.domain.HomeRepository
import com.example.utils.helper.ResultWrapper

class HomeRepositoryImpl(
    private val networkService: NetworkService
) : HomeRepository {

    override suspend fun getCategories(): ResultWrapper<CategoryList> {
        return networkService.getCategories()
    }

    override suspend fun getProducts(category: Int?): ResultWrapper<ProductList> {
        return networkService.getProducts(category)
    }
}