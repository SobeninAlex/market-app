package com.example.data.dto.response

import com.example.data.dto.CartDto
import com.example.domain.entity.CartList
import kotlinx.serialization.Serializable

@Serializable
data class CartListResponse(
    val carts: List<CartDto>,
    val msg: String
) {

    fun toCartList() = CartList(
        carts = carts.map { it.toCart() },
        msg = msg
    )

}
