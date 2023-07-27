package com.example.cryptoinfo.presentation

import android.app.Application
import com.example.cryptoinfo.di.DaggerApplicationComponent

class CoinApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}