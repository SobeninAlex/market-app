package com.example.data.dto.response

import com.example.data.dto.CategoryDto
import com.example.domain.entity.CategoryList
import kotlinx.serialization.Serializable

@Serializable
data class CategoryListResponse(
    val data: List<CategoryDto>,
    val msg: String
) {

    fun toCategoryList() = CategoryList(
        categories = data.map { it.toCategory() },
        msg = msg
    )

}