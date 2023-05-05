//package com.example.cryptoinfo.domain.clean
//
//import androidx.lifecycle.LiveData
//import com.example.cryptoinfo.data.database.model.CoinInfoDbModel
//import io.reactivex.rxjava3.disposables.Disposable
//
//interface CoinPriceInfoRepository {
//
//    fun getListAllCoinPriceInfo(): LiveData<List<CoinInfoDbModel>>
//
//    fun getCoinPriceInfo(fsym: String?): LiveData<CoinInfoDbModel>
//
//    fun getTopListAndInsert(): Disposable
//
//}