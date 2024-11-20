package com.example.data.dto

import com.example.domain.Category
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val id: Int,
    val image: String,
    val title: String
) {

    fun toCategory() = Category(
        id = id,
        image = image,
        title = title
    )

}
