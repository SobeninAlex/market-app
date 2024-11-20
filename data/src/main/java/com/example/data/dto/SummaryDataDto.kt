package com.example.data.dto

import com.example.domain.Summary
import kotlinx.serialization.Serializable

@Serializable
data class SummaryDataDto(
    val discount: Double,
    val items: List<CartDto>,
    val shipping: Double,
    val subtotal: Double,
    val tax: Double,
    val total: Double,
) {

    fun toSummaryData() = Summary(
        discount = discount,
        items = items.map { it.toCart() },
        shipping = shipping,
        subtotal = subtotal,
        tax = tax,
        total = total,
    )

}
