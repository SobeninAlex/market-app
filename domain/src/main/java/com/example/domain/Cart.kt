package com.example.domain

data class Cart(
    val id: Int,
    val productId: Int,
    val price: Double,
    val imageUrl: String? = null,
    val quantity: Int,
    val productName: String,
    val changeQuantityProcess: Boolean = false,
    val deleteProcess: Boolean = false,
) {

    companion object {
        val FAKE = Cart(
            id = 681,
            productId = 9,
            price = 150.0,
            imageUrl = "https://robbreport.com/wp-content/uploads/2022/07/tomcruiseaviators.jpg?w=1000",
            quantity = 1,
            productName = "Ray-Ban Aviator Sunglasses"
        )
    }

}
