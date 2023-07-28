package com.example.cryptoinfo.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoinfo.data.database.AppDatabase
import com.example.cryptoinfo.data.mapper.CoinMapper
import com.example.cryptoinfo.data.network.ApiFactory
import com.example.cryptoinfo.data.network.ApiService
import com.example.cryptoinfo.data.worker.RefreshDataWorkerFactory
import com.example.cryptoinfo.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var refreshDataWorkerFactory: RefreshDataWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }



    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().setWorkerFactory(refreshDataWorkerFactory).build()
    }

}