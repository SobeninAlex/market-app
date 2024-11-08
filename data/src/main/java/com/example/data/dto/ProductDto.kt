package com.example.data.dto

import com.example.domain.entity.Product
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val categoryId: Int,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val title: String
) {

    fun toProduct() = Product(
        categoryId = categoryId,
        description = description,
        id = id,
        image = image,
        price = price,
        title = title
    )

}
