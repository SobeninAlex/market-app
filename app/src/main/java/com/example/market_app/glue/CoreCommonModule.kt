package com.example.market_app.glue

import com.example.common.AndroidLogger
import com.example.common.AndroidResources
import com.example.common.Core
import com.example.common.CoreProvider
import com.example.common.DefaultErrorHandler
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val coreCommonModule = module {

    single<CoreProvider> {
        CoreProvider(
            appContext = get()
        )
    }

    single<CoroutineScope> {
        Core.globalScope
    }

    single<AndroidLogger> {
        Core.logger
    }

    single<AndroidResources> {
        Core.resources
    }

    single<DefaultErrorHandler> {
        Core.errorHandler
    }
}