package com.example.cryptoinfo.domain.clean

import androidx.lifecycle.LiveData
import com.example.cryptoinfo.data.model.CoinPriceInfo
import io.reactivex.rxjava3.disposables.Disposable

interface CoinPriceInfoRepository {

    fun getListAllCoinPriceInfo(): LiveData<List<CoinPriceInfo>>

    fun getCoinPriceInfo(fsym: String?): LiveData<CoinPriceInfo>

    fun getTopListAndInsert(): Disposable

}