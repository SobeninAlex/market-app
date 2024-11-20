package com.example.market_app.glue.feature.detail

import com.example.data.dto.request.AddProductToCartRequest
import com.example.data.network.NetworkService
import com.example.details.domain.DetailRepository
import com.example.domain.Cart
import com.example.utils.helper.NetworkResultWrapper

class DetailRepositoryImpl(
    private val networkService: NetworkService
) : DetailRepository {

    override suspend fun addProductToCart(
        productId: Int,
        quantity: Int
    ): NetworkResultWrapper<List<Cart>> {
        val request = AddProductToCartRequest(
            productId = productId,
            quantity = quantity
        )
        return networkService.addProductToCart(request)
    }
}