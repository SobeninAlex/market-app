package com.example.market_app.glue.feature.home

import com.example.home.domain.HomeRepository
import com.example.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureHomeModule = module {
    single<HomeRepository> {
        HomeRepositoryImpl(
            networkService = get()
        )
    }

    viewModel {
        HomeViewModel(
            homeRepository = get()
        )
    }
}