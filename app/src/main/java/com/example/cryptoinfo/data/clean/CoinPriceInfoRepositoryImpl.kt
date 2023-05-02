//package com.example.cryptoinfo.data.clean
//
//import android.app.Application
//import android.util.Log
//import androidx.lifecycle.LiveData
//import com.example.cryptoinfo.data.network.ApiFactory
//import com.example.cryptoinfo.data.database.AppDatabase
//import com.example.cryptoinfo.domain.clean.CoinPriceInfoRepository
//import com.example.cryptoinfo.data.database.model.CoinInfoDbModel
//import com.example.cryptoinfo.data.mapper.CoinMapper
//import io.reactivex.rxjava3.disposables.Disposable
//import io.reactivex.rxjava3.schedulers.Schedulers
//import java.util.concurrent.TimeUnit
//
//class CoinPriceInfoRepositoryImpl(application: Application): CoinPriceInfoRepository {
//
//    private val coinPriceInfoDAO = AppDatabase.getInstance(application).getDAO()
//    private val mapper = CoinMapper()
//
//    override fun getListAllCoinPriceInfo(): LiveData<List<CoinInfoDbModel>> {
//        return coinPriceInfoDAO.getListAllCoinPriceInfo()
//    }
//
//    override fun getCoinPriceInfo(fsym: String?): LiveData<CoinInfoDbModel> {
//        return coinPriceInfoDAO.getCoinPriceInfo(fsym)
//    }
//
//    override fun getTopListAndInsert(): Disposable {
//        val disposable = ApiFactory.apiService.getTopList(limit = 50)
//            .flatMap { ApiFactory.apiService.getCoinPriceInfoRawData(mapper.mapNamesListToString(it)) }
//            .map { mapper.getListCoinInfoFromRawData(it) }
//            .delaySubscription(10, TimeUnit.SECONDS)
//            .repeat()
//            .retry()
//            .subscribeOn(Schedulers.io())
//            .subscribe({
//                coinPriceInfoDAO.insertListCoinPriceInfo(mapper.mapListDtoToDbModel(it))
//                Log.d("test_load", "Success: " + it.toString())
//            }, {
//                Log.d("test_load", "Failure: " + it.message.toString())
//            })
//
//        return disposable
//    }
//
//}