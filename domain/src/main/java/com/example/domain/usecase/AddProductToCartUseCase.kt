package com.example.domain.usecase

import com.example.domain.entity.request.AddCartRequest
import com.example.domain.repository.CartRepository

class AddProductToCartUseCase(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(request: AddCartRequest) =
        cartRepository.addProductToCart(request)

}