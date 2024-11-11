package com.example.data.di

import com.example.data.network.NetworkServiceImpl
import com.example.data.repository.CartRepositoryImpl
import com.example.data.repository.CategoryRepositoryImpl
import com.example.data.repository.ProductRepositoryImpl
import com.example.domain.network.NetworkService
import com.example.domain.repository.CartRepository
import com.example.domain.repository.CategoryRepository
import com.example.domain.repository.ProductRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository> {
        ProductRepositoryImpl(
            networkService = get()
        )
    }

    single<CategoryRepository> {
        CategoryRepositoryImpl(
            networkService = get()
        )
    }

    single<CartRepository> {
        CartRepositoryImpl(
            networkService = get()
        )
    }
}