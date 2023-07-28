package com.example.cryptoinfo.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptoinfo.data.database.CoinInfoDAO
import com.example.cryptoinfo.data.mapper.CoinMapper
import com.example.cryptoinfo.data.worker.RefreshDataWorker
import com.example.cryptoinfo.domain.CoinInfoEntity
import com.example.cryptoinfo.domain.CoinInfoRepository
import javax.inject.Inject

class CoinInfoRepositoryImpl @Inject constructor(
    private val application: Application,
    private val mapper: CoinMapper,
    private val dao: CoinInfoDAO
    ): CoinInfoRepository {

    override fun getCoinInfoList(): LiveData<List<CoinInfoEntity>> {
        return Transformations.map(dao.getListAllCoinPriceInfo()){
            mapper.mapListDbModelToEntity(it)
        }
    }

    override fun getCoinInfo(fsym: String?): LiveData<CoinInfoEntity> {
        return Transformations.map(dao.getCoinPriceInfo(fsym)){
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadAndInsertData() {
        val worker = WorkManager.getInstance(application)
        worker.enqueueUniqueWork(RefreshDataWorker.WORKER_NAME,
            ExistingWorkPolicy.REPLACE,
        RefreshDataWorker.makeOneTimeWorkRequest())
    }

}