package com.example.cryptoinfo.domain.clean

import androidx.lifecycle.LiveData
import com.example.cryptoinfo.data.clean.CoinPriceInfoRepositoryImpl
import com.example.cryptoinfo.domain.pojo.CoinPriceInfo

class GetCoinPriceInfoUC(private val repositoryImpl: CoinPriceInfoRepositoryImpl) {
    fun getCoinPriceInfo(fsym: String?): LiveData<CoinPriceInfo> {
        return repositoryImpl.getCoinPriceInfo(fsym)
    }
}