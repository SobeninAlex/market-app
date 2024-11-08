package com.example.data.dto.response

import com.example.data.dto.ProductDto
import com.example.domain.entity.ProductList
import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponse(
    val data: List<ProductDto>,
    val msg: String
) {

    fun toProductList() = ProductList(
        products = data.map { it.toProduct() },
        msg = msg
    )

}