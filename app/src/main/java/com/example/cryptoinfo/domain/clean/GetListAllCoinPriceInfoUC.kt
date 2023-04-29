package com.example.cryptoinfo.domain.clean

import androidx.lifecycle.LiveData
import com.example.cryptoinfo.data.clean.CoinPriceInfoRepositoryImpl
import com.example.cryptoinfo.domain.pojo.CoinPriceInfo

class GetListAllCoinPriceInfoUC(private val repositoryImpl: CoinPriceInfoRepositoryImpl) {
    fun getListAllCoinPriceInfo(): LiveData<List<CoinPriceInfo>> {
        return repositoryImpl.getListAllCoinPriceInfo()
    }
}