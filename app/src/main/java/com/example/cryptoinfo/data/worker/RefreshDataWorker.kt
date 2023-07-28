package com.example.cryptoinfo.data.worker

import android.content.Context
import androidx.work.*
import com.example.cryptoinfo.data.database.CoinInfoDAO
import com.example.cryptoinfo.data.database.model.CoinInfoDbModel
import com.example.cryptoinfo.data.mapper.CoinMapper
import com.example.cryptoinfo.data.network.ApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

class RefreshDataWorker (
    context: Context,
    workerParameters: WorkerParameters,
    private val dao: CoinInfoDAO,
    private val apiService: ApiService,
    private val mapper: CoinMapper
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        while (true){
            try {
                val coinNamesListDto = apiService.getTopList(limit = 50)
                val coinNamesListToString = mapper.mapNamesListToString(coinNamesListDto)
                val coinInfoJsonContainerDto = apiService
                    .getCoinPriceInfoRawData(fsyms = coinNamesListToString)
                val listCoinInfoDto = mapper.mapJsonContainerToListCoinInfoDto(coinInfoJsonContainerDto)
                val listCoinInfoDbModel = mapper.mapListDtoToDbModel(listCoinInfoDto)
                insertListCoinInfoToDb(listCoinInfoDbModel)
            } catch (e: Exception) {
                e.printStackTrace()
                            }
            delay(10_000)
        }
    }

    private suspend fun insertListCoinInfoToDb(list: List<CoinInfoDbModel>) {
        dao.insertListCoinInfo(list)
    }

    companion object {
        const val WORKER_NAME = "RefreshDataWorker"

        fun makeOneTimeWorkRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }

    class Factory @Inject constructor(
        private val dao: CoinInfoDAO,
        private val apiService: ApiService,
        private val mapper: CoinMapper
    ) : ChildWorkerFactory {
        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshDataWorker(context, workerParameters, dao, apiService, mapper)
        }

    }

}