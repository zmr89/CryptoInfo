package com.example.cryptoinfo.di

import android.app.Application
import com.example.cryptoinfo.data.database.AppDatabase
import com.example.cryptoinfo.data.database.CoinInfoDAO
import com.example.cryptoinfo.data.network.ApiFactory
import com.example.cryptoinfo.data.network.ApiService
import com.example.cryptoinfo.data.repository.CoinInfoRepositoryImpl
import com.example.cryptoinfo.domain.CoinInfoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindsRepository(repositoryImpl: CoinInfoRepositoryImpl): CoinInfoRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideCoinInfoDAO(application: Application): CoinInfoDAO {
            return AppDatabase.getInstance(application).getDAO()
        }

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

    }
}