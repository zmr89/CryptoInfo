package com.example.cryptoinfo.domain.clean

import com.example.cryptoinfo.data.clean.CoinPriceInfoRepositoryImpl
import io.reactivex.rxjava3.disposables.Disposable

class GetTopListAndInsertUC(private val repositoryImpl: CoinPriceInfoRepositoryImpl) {
    fun getTopListAndInsert(): Disposable {
        return repositoryImpl.getTopListAndInsert()
    }
}