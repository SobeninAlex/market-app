package com.example.data.dto.response

import com.example.data.dto.CategoryDto
import kotlinx.serialization.Serializable

@Serializable
data class CategoryListResponse(
    val data: List<CategoryDto>,
    val msg: String
)