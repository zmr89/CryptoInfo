package com.example.cryptoinfo.domain.clean

import com.example.cryptoinfo.data.clean.CoinPriceInfoRepositoryImpl
import com.example.cryptoinfo.domain.pojo.CoinPriceInfo

class InsertListCoinPriceInfoUC(private val repositoryImpl: CoinPriceInfoRepositoryImpl) {
    fun insertListCoinPriceInfo(list: List<CoinPriceInfo>) {
        repositoryImpl.insertListCoinPriceInfo(list)
    }
}