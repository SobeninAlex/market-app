package com.example.market_app.glue.feature.cart

import com.example.cart.domain.CartRepository
import com.example.data.dto.request.AddProductToCartRequest
import com.example.data.network.NetworkService
import com.example.domain.Cart
import com.example.domain.Summary
import com.example.utils.helper.NetworkResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CartRepositoryImpl(
    private val networkService: NetworkService
) : CartRepository {

    override suspend fun getCartSummary(userId: Int): NetworkResultWrapper<Summary> {
        return networkService.getCartSummary(userId)
    }

    override val cart: Flow<NetworkResultWrapper<List<Cart>>> = flow {
        emit(networkService.getCart())
    }

    override suspend fun updateQuantity(cart: Cart): NetworkResultWrapper<List<Cart>> {
        val request = AddProductToCartRequest(
            productId = cart.productId,
            quantity = cart.quantity
        )
        return networkService.updateQuantity(request = request, cartId = cart.id)
    }

    override suspend fun removeItem(cartId: Int, userId: Int): NetworkResultWrapper<List<Cart>> {
        return networkService.removeItem(cartId = cartId, userId = userId)
    }
}