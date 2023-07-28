package com.example.cryptoinfo.di

import androidx.lifecycle.ViewModel
import com.example.cryptoinfo.presentation.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    @Binds
    fun bindCoinViewModel(coinViewModel: CoinViewModel): ViewModel

}