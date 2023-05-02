package com.example.cryptoinfo.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.cryptoinfo.data.database.AppDatabase
import com.example.cryptoinfo.data.database.model.CoinInfoDbModel
import com.example.cryptoinfo.data.mapper.CoinMapper
import com.example.cryptoinfo.data.network.ApiFactory
import com.example.cryptoinfo.domain.sumin.CoinInfoEntity
import com.example.cryptoinfo.domain.sumin.CoinInfoRepository
import kotlinx.coroutines.delay

class CoinInfoRepositoryImpl(application: Application): CoinInfoRepository {

    private val dao = AppDatabase.getInstance(application).getDAO()
    private val apiService = ApiFactory.apiService
    private val mapper = CoinMapper()

    override fun getCoinPriceInfoList(): LiveData<List<CoinInfoEntity>> {
        return Transformations.map(dao.getListAllCoinPriceInfo()){
            mapper.mapListDbModelToEntity(it)
        }
    }

    override fun getCoinPriceInfo(fsym: String?): LiveData<CoinInfoEntity> {
        return Transformations.map(dao.getCoinPriceInfo(fsym)){
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadAndInsertData() {
        while (true){
            val coinNamesListDto = apiService.getTopList(limit = 50)
            val coinNamesListToString = mapper.mapNamesListToString(coinNamesListDto)
            val coinInfoJsonContainerDto = apiService
                .getCoinPriceInfoRawData(fsyms = coinNamesListToString)
            val listCoinInfoDto = mapper.mapJsonContainerToListCoinInfoDto(coinInfoJsonContainerDto)
            val listCoinInfoDbModel = mapper.mapListDtoToDbModel(listCoinInfoDto)
            insertListCoinInfoToDb(listCoinInfoDbModel)
            delay(10_000)
        }
    }

    private fun insertListCoinInfoToDb(list: List<CoinInfoDbModel>) {
        dao.insertListCoinInfo(list)
    }

}