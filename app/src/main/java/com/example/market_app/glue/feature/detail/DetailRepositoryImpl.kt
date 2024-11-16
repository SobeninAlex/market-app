package com.example.market_app.glue.feature.detail

import com.example.data.network.NetworkService
import com.example.details.domain.DetailRepository
import com.example.utils.domain.CartList
import com.example.utils.domain.request.AddCartRequest
import com.example.utils.helper.ResultWrapper

class DetailRepositoryImpl(
    private val networkService: NetworkService
) : DetailRepository {

    override suspend fun addProductToCart(request: AddCartRequest): ResultWrapper<CartList> {
        return networkService.addProductToCart(request)
    }
}