package com.example.cryptoinfo.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoinfo.data.worker.CoinWorkerFactory
import com.example.cryptoinfo.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var coinWorkerFactory: CoinWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }



    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().setWorkerFactory(coinWorkerFactory).build()
    }

}