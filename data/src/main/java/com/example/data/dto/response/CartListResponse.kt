package com.example.data.dto.response

import com.example.data.dto.CartDto
import kotlinx.serialization.Serializable

@Serializable
data class CartListResponse(
    val data: List<CartDto>,
    val msg: String
)
