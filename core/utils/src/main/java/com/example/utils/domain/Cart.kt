package com.example.utils.domain

import androidx.compose.runtime.Immutable

data class Cart(
    val id: Int,
    val productId: Int,
//    val userId: Int,
//    val name: String,
    val price: Double,
    val imageUrl: String?,
    val quantity: Int,
    val productName: String,
    val changeQuantityProcess: Boolean = false
) {

    companion object {
        val FAKE = Cart(
            id = 681,
            productId = 9,
//            userId = 1,
//            name = "Ray-Ban Aviator Sunglasses",
            price = 150.0,
            imageUrl = "https://robbreport.com/wp-content/uploads/2022/07/tomcruiseaviators.jpg?w=1000",
            quantity = 1,
            productName = "Ray-Ban Aviator Sunglasses"
        )
    }

}
