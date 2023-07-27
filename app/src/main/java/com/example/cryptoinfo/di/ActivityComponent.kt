//package com.example.cryptoinfo.di
//
//import android.app.Application
//import android.content.Context
//import com.example.cryptoinfo.presentation.CoinPriceListActivity
//import dagger.BindsInstance
//import dagger.Component
//import dagger.Subcomponent
//
//@Subcomponent(modules = [ViewModelModule::class])
//interface ActivityComponent {
//
//    fun inject(activity: CoinPriceListActivity)
//
//    @Subcomponent.Factory
//    interface Factory {
//
//        fun create(
//            @BindsInstance application: Application
//        ) : ActivityComponent
//
//    }
//}