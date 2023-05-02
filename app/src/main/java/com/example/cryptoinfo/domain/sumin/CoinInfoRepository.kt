package com.example.cryptoinfo.domain.sumin

import androidx.lifecycle.LiveData

interface CoinInfoRepository {

    fun getCoinPriceInfoList(): LiveData<List<CoinInfoEntity>>
    fun getCoinPriceInfo(fsym: String?): LiveData<CoinInfoEntity>
    suspend fun loadAndInsertData()

}