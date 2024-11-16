package com.example.data.dto.response

import com.example.data.dto.CartDto
import com.example.utils.domain.CartList
import kotlinx.serialization.Serializable

@Serializable
data class CartListResponse(
    val data: List<CartDto>,
    val msg: String
) {

    fun toCartList() = CartList(
        carts = data.map { it.toCart() },
        msg = msg
    )

}
