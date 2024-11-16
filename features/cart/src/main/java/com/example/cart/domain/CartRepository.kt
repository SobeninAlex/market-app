package com.example.cart.domain

import com.example.utils.domain.CartList
import com.example.utils.helper.ResultWrapper

interface CartRepository {

    suspend fun getCart(): ResultWrapper<CartList>

}