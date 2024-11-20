package com.example.domain

data class Summary(
    val discount: Double,
    val items: List<Cart>,
    val shipping: Double,
    val subtotal: Double,
    val tax: Double,
    val total: Double,
) {

    companion object {
        val DEFAULT = Summary(
            discount = 0.0,
            items = emptyList(),
            shipping = 0.0,
            subtotal = 0.0,
            tax = 0.0,
            total = 0.0
        )

        val FAKE = Summary(
            discount = 9.999,
            items = listOf(
                Cart.FAKE,
                Cart.FAKE
            ),
            shipping = 9.99,
            subtotal = 9.9,
            tax = 0.9,
            total = 9.0
        )
    }

}
