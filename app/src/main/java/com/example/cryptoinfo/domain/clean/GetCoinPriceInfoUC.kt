//package com.example.cryptoinfo.domain.clean
//
//import androidx.lifecycle.LiveData
//import com.example.cryptoinfo.data.clean.CoinPriceInfoRepositoryImpl
//import com.example.cryptoinfo.data.database.model.CoinInfoDbModel
//
//class GetCoinPriceInfoUC(private val repositoryImpl: CoinPriceInfoRepositoryImpl) {
//    fun getCoinPriceInfo(fsym: String?): LiveData<CoinInfoDbModel> {
//        return repositoryImpl.getCoinPriceInfo(fsym)
//    }
//}