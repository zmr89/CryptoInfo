package com.example.cryptoinfo.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cryptoinfo.data.api.ApiFactory
import com.example.cryptoinfo.data.clean.CoinPriceInfoRepositoryImpl
import com.example.cryptoinfo.data.database.AppDatabase
import com.example.cryptoinfo.domain.clean.GetCoinPriceInfoUC
import com.example.cryptoinfo.domain.clean.GetListAllCoinPriceInfoUC
import com.example.cryptoinfo.domain.clean.InsertListCoinPriceInfoUC
import com.example.cryptoinfo.domain.pojo.CoinPriceInfo
import com.example.cryptoinfo.domain.pojo.CoinPriceInfoRawData
import com.google.gson.Gson
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {
 //*
    private val coinPriceInfoRepositoryImpl = CoinPriceInfoRepositoryImpl(application)
    val getListAllCoinPriceInfoUC = GetListAllCoinPriceInfoUC(coinPriceInfoRepositoryImpl)
    val getCoinPriceInfoUC = GetCoinPriceInfoUC(coinPriceInfoRepositoryImpl)
    val insertListCoinPriceInfoUC = InsertListCoinPriceInfoUC(coinPriceInfoRepositoryImpl)
//*
//    private val coinPriceInfoDAO = AppDatabase.getInstance(application).getCoinPriceInfoDAO()
    private val compositeDisposable = CompositeDisposable()

//    val allCoinPriceInfoListFromDB = coinPriceInfoDAO.getListAllCoinPriceInfo()
    val allCoinPriceInfoListFromDB = getListAllCoinPriceInfoUC.getListAllCoinPriceInfo()

    fun getCoinPriceInfoFromDB(fsym: String?): LiveData<CoinPriceInfo> {
//        return coinPriceInfoDAO.getCoinPriceInfo(fsym)
        return getCoinPriceInfoUC.getCoinPriceInfo(fsym)
    }

    init {
        getTopList()
    }

    private fun getTopList() {
        val disposable = ApiFactory.apiService.getTopList(limit = 50)
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getCoinPriceInfoRawData(fsyms = it as String) }
            .map { getListCoinPriceInfoFromRawData(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
//                coinPriceInfoDAO.insertListCoinPriceInfo(it)
                insertListCoinPriceInfoUC.insertListCoinPriceInfo(it)
                Log.d("test_load", "Success: " + it.toString())
            }, {
                Log.d("test_load", "Failure: " + it.message.toString())
            })
        compositeDisposable.add(disposable)
    }


    private fun getListCoinPriceInfoFromRawData(coinPriceInfoRawData: CoinPriceInfoRawData): List<CoinPriceInfo> {
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


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}