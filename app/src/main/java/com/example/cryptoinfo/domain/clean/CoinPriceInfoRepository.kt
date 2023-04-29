package com.example.cryptoinfo.domain.clean

import androidx.lifecycle.LiveData
import com.example.cryptoinfo.domain.pojo.CoinPriceInfo

interface CoinPriceInfoRepository {

    fun getListAllCoinPriceInfo(): LiveData<List<CoinPriceInfo>>

    fun getCoinPriceInfo(fsym: String?): LiveData<CoinPriceInfo>

    fun insertListCoinPriceInfo(list: List<CoinPriceInfo>)

}