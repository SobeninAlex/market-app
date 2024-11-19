package com.example.data.dto.request

import com.example.utils.domain.request.AddCartRequest
import kotlinx.serialization.Serializable

@Serializable
data class AddCartRequestDto(
    val productId: Int,
    val quantity: Int,
) {

    fun toAddCartRequest() = AddCartRequest(
        productId = productId,
        quantity = quantity,
    )

    companion object {
        fun fromAddCartRequest(addCartRequest: AddCartRequest) = AddCartRequestDto(
            productId = addCartRequest.productId,
            quantity = addCartRequest.quantity,
        )
    }
}
