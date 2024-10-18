package com.example.market_app.di

import org.koin.dsl.module

val presentationModule = module {
    includes(
        viewModelModule,
    )
}