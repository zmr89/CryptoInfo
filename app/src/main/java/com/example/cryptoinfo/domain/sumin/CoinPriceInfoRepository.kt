package com.example.cryptoinfo.domain.sumin

import androidx.lifecycle.LiveData

interface CoinPriceInfoRepository {

    fun getCoinPriceInfoList(): LiveData<List<CoinPriceInfoEntity>>
    fun getCoinPriceInfo(): LiveData<CoinPriceInfoEntity>

}