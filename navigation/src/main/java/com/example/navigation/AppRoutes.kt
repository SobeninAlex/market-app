package com.example.navigation

import com.example.domain.Product
import kotlinx.serialization.Serializable

@Serializable
data object HomeGraph {

    @Serializable
    data object HomeRoute

    @Serializable
    data class ProductDetailsRoute(val product: Product)

}

@Serializable
data object CartGraph {

    @Serializable
    data object CartRoute

    @Serializable
    data object CheckoutRoute

}

@Serializable
data object ProfileGraph {

    @Serializable
    data object ProfileRoute

}

