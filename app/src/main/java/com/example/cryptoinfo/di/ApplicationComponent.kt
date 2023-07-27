package com.example.cryptoinfo.di

import android.app.Application
import android.content.Context
import com.example.cryptoinfo.presentation.CoinPriceListActivity
import com.example.cryptoinfo.presentation.DetailFragment
import dagger.BindsInstance
import dagger.Component

//@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)
    fun inject(fragment: DetailFragment)

//    fun getActivityComponentFactory() : ActivityComponent.Factory

    @Component.Factory
    interface ApplicationComponentFactory{

        fun create(
            @BindsInstance application: Application
        ) : ApplicationComponent

    }
}