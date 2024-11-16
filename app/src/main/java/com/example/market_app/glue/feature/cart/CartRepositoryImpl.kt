package com.example.market_app.glue.feature.cart

import com.example.cart.domain.CartRepository
import com.example.data.network.NetworkService
import com.example.utils.domain.CartList
import com.example.utils.helper.ResultWrapper

class CartRepositoryImpl(
    private val networkService: NetworkService
) : CartRepository {

    override suspend fun getCart(): ResultWrapper<CartList> {
        return networkService.getCart()
    }
}