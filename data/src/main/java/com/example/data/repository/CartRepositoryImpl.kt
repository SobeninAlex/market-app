package com.example.data.repository

import com.example.domain.entity.Cart
import com.example.domain.entity.CartList
import com.example.domain.entity.request.AddCartRequest
import com.example.domain.network.NetworkService
import com.example.domain.network.ResultWrapper
import com.example.domain.repository.CartRepository

class CartRepositoryImpl(
    private val networkService: NetworkService
) : CartRepository {

    override suspend fun addProductToCart(request: AddCartRequest): ResultWrapper<CartList> {
        return networkService.addProductToCart(request)
    }

}