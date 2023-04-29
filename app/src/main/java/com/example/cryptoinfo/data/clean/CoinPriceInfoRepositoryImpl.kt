package com.example.cryptoinfo.data.clean

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.cryptoinfo.data.database.AppDatabase
import com.example.cryptoinfo.domain.clean.CoinPriceInfoRepository
import com.example.cryptoinfo.domain.pojo.CoinPriceInfo

class CoinPriceInfoRepositoryImpl(application: Application): CoinPriceInfoRepository {

    private val coinPriceInfoDAO = AppDatabase.getInstance(application).getCoinPriceInfoDAO()

    override fun getListAllCoinPriceInfo(): LiveData<List<CoinPriceInfo>> {
        return coinPriceInfoDAO.getListAllCoinPriceInfo()
    }

    override fun getCoinPriceInfo(fsym: String?): LiveData<CoinPriceInfo> {
        return coinPriceInfoDAO.getCoinPriceInfo(fsym)
    }

    override fun insertListCoinPriceInfo(list: List<CoinPriceInfo>) {
        coinPriceInfoDAO.insertListCoinPriceInfo(list)
    }

}