package com.example.data.dto

import com.example.data.dto.request.AddCartRequestDto
import com.example.utils.domain.Cart
import com.example.utils.domain.request.AddCartRequest
import kotlinx.serialization.Serializable

@Serializable
data class CartDto(
    val id: Int,
    val productId: Int,
//    val userId: Int,
//    val name: String,
    val price: Double,
    val imageUrl: String?,
    val quantity: Int,
    val productName: String
) {

    fun toCart() = Cart(
        id = id,
        productId = productId,
//        userId = userId,
//        name = name,
        price = price,
        imageUrl = imageUrl,
        quantity = quantity,
        productName = productName
    )

//    companion object {
//        fun fromCart(cart: Cart) = CartDto(
//            id = cart.id,
//            productId = cart.productId,
////            userId = cart.userId,
////            name = cart.name,
//            price = cart.price,
//            imageUrl = cart.imageUrl,
//            quantity = cart.quantity,
//            productName = cart.productName
//        )
//    }

}
