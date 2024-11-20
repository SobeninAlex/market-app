package com.example.data.dto.response

import com.example.data.dto.SummaryDataDto
import kotlinx.serialization.Serializable

@Serializable
data class CartSummaryResponse(
    val data: SummaryDataDto,
    val msg: String
)
