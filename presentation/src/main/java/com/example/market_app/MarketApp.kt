package com.example.market_app

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.market_app.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarketApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarketApp)
            modules(
                listOf(
                    presentationModule,
                    dataModule,
                    domainModule
                )
            )
        }
    }

}