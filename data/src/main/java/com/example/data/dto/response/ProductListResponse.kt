package com.example.data.dto.response

import com.example.data.dto.ProductDto
import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponse(
    val data: List<ProductDto>,
    val msg: String
)