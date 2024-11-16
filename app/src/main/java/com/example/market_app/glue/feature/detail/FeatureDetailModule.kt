package com.example.market_app.glue.feature.detail

import com.example.details.domain.DetailRepository
import com.example.details.presentation.ProductDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureDetailModule = module {
    single<DetailRepository> {
        DetailRepositoryImpl(
            networkService = get()
        )
    }

    viewModel {
        ProductDetailsViewModel(
            detailRepository = get()
        )
    }
}