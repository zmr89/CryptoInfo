package com.example.cryptoinfo.di

import android.app.Application
import com.example.cryptoinfo.data.database.AppDatabase
import com.example.cryptoinfo.data.database.CoinInfoDAO
import com.example.cryptoinfo.data.repository.CoinInfoRepositoryImpl
import com.example.cryptoinfo.domain.CoinInfoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindsRepository(repositoryImpl: CoinInfoRepositoryImpl): CoinInfoRepository

    companion object {

        @Provides
        fun provideCoinInfoDAO(application: Application): CoinInfoDAO {
            return AppDatabase.getInstance(application).getDAO()
        }

    }
}