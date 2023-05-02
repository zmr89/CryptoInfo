package com.example.cryptoinfo.data.clean

import com.example.cryptoinfo.data.model.CoinPriceInfo
import com.example.cryptoinfo.data.model.CoinPriceInfoRawData
import com.google.gson.Gson

class CoinPriceInfoMapper {

    fun getListCoinPriceInfoFromRawData(coinPriceInfoRawData: CoinPriceInfoRawData): List<CoinPriceInfo> {
        val listCoinPriceInfo = arrayListOf<CoinPriceInfo>()

        val rawJsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject
        if (rawJsonObject == null) return listCoinPriceInfo
        val coinKeySet = rawJsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJsonObject = rawJsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJsonObject.keySet()
            for (currencyKey in currencyKeySet) {
                val coinPriceInfoJsonObject = currencyJsonObject.getAsJsonObject(currencyKey)
                val coinPriceInfo = Gson().fromJson<CoinPriceInfo>(coinPriceInfoJsonObject, CoinPriceInfo::class.java)
                listCoinPriceInfo.add(coinPriceInfo)
            }

        }
        return listCoinPriceInfo
    }


}