package com.example.cryptoinfo.domain.sumin

import androidx.lifecycle.LiveData

interface CoinInfoRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfoEntity>>
    fun getCoinInfo(fsym: String?): LiveData<CoinInfoEntity>
    suspend fun loadAndInsertData()

}