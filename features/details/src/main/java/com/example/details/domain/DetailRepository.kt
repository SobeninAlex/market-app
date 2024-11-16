package com.example.details.domain

import com.example.utils.domain.CartList
import com.example.utils.domain.request.AddCartRequest
import com.example.utils.helper.ResultWrapper

interface DetailRepository {

    suspend fun addProductToCart(request: AddCartRequest) : ResultWrapper<CartList>

}