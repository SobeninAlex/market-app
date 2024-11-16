package com.example.market_app.glue.feature.cart

import com.example.cart.domain.CartRepository
import com.example.cart.presentation.CartViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureCartModule = module {
    single<CartRepository> {
        CartRepositoryImpl(
            networkService = get()
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get()
        )
    }
}