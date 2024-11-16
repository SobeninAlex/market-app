package com.example.market_app

import android.app.Application
import com.example.common.Core
import com.example.common.CoreProvider
import com.example.data.di.dataModule
import com.example.market_app.glue.coreCommonModule
import com.example.market_app.glue.feature.cart.featureCartModule
import com.example.market_app.glue.feature.detail.featureDetailModule
import com.example.market_app.glue.feature.home.featureHomeModule
import com.example.market_app.glue.feature.profile.featureProfileModule
import org.koin.android.ext.android.get
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarketApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarketApp)
            modules(
                listOf(
                    dataModule,
                    featureCartModule,
                    featureHomeModule,
                    featureDetailModule,
                    featureProfileModule,
                    coreCommonModule
                )
            )
        }

        Core.init(coreProvider = get())
    }

}