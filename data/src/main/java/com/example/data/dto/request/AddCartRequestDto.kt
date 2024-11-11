package com.example.data.dto.request

import com.example.domain.entity.request.AddCartRequest
import kotlinx.serialization.Serializable

@Serializable
data class AddCartRequestDto(
    val productId: Int,
    val productName: String,
    val price: Double,
    val quantity: Int,
    val userId: Int,
) {

    fun toAddCartRequest() = AddCartRequest(
        productId = productId,
        productName = productName,
        price = price,
        quantity = quantity,
        userId = userId,
    )

    companion object {
        fun fromAddCartRequest(addCartRequest: AddCartRequest) = AddCartRequestDto(
            productId = addCartRequest.productId,
            productName = addCartRequest.productName,
            price = addCartRequest.price,
            quantity = addCartRequest.quantity,
            userId = addCartRequest.userId,
        )
    }
}
