package com.example.navigation

import com.example.utils.domain.Product
import kotlinx.serialization.Serializable

@Serializable
data object HomeGraph {

    @Serializable
    data object HomeRoute

    @Serializable
    data class ProductDetailsRoute(val product: Product)

}

@Serializable
data object BasketGraph {

    @Serializable
    data object BasketRoute

}

@Serializable
data object ProfileGraph {

    @Serializable
    data object ProfileRoute

}

