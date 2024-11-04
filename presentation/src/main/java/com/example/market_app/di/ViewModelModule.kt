package com.example.market_app.di

import com.example.market_app.ui.screens.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(
            getCategoriesUseCase = get(),
            getProductUseCase = get()
        )
    }
}