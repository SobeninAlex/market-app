package com.example.domain.usecase

import com.example.domain.repository.CartRepository
import com.example.domain.repository.ProductRepository

class GetCartUseCase(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke() =
        cartRepository.getCart()

}