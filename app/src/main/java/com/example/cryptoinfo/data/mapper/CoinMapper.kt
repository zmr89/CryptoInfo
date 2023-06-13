package com.example.cryptoinfo.data.mapper

import com.example.cryptoinfo.data.database.model.CoinInfoDbModel
import com.example.cryptoinfo.data.network.model.CoinInfoDto
import com.example.cryptoinfo.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptoinfo.data.network.model.CoinNamesListDto
import com.example.cryptoinfo.domain.CoinInfoEntity
import com.google.gson.Gson
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class CoinMapper {

    fun mapDtoToDbModel(coinInfoDto: CoinInfoDto): CoinInfoDbModel = CoinInfoDbModel(
        imageurl = getFullImageUrl(coinInfoDto.imageurl),
        tosymbol = coinInfoDto.tosymbol,
        fromsymbol = coinInfoDto.fromsymbol,
        price = coinInfoDto.price,
        lowday = coinInfoDto.lowday,
        highday = coinInfoDto.highday,
        lastmarket = coinInfoDto.lastmarket,
        lastupdate = coinInfoDto.lastupdate
    )

    fun mapListDtoToDbModel(listCoinInfoDto: List<CoinInfoDto>): List<CoinInfoDbModel> =
        listCoinInfoDto.map { mapDtoToDbModel(it) }

    fun mapDbModelToEntity(coinInfoDbModel: CoinInfoDbModel): CoinInfoEntity = CoinInfoEntity(
        imageurl = coinInfoDbModel.imageurl,
        tosymbol = coinInfoDbModel.tosymbol,
        fromsymbol = coinInfoDbModel.fromsymbol,
        price = coinInfoDbModel.price,
        lowday = coinInfoDbModel.lowday,
        highday = coinInfoDbModel.highday,
        lastmarket = coinInfoDbModel.lastmarket,
        lastupdate = convertTimestampToTime(coinInfoDbModel.lastupdate)
    )

    fun mapListDbModelToEntity(listCoinInfoDbModel: List<CoinInfoDbModel>): List<CoinInfoEntity> =
        listCoinInfoDbModel.map { mapDbModelToEntity(it) }

    fun mapJsonContainerToListCoinInfoDto(coinInfoJsonContainerDto: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val listCoinInfoDto = mutableListOf<CoinInfoDto>()

        val rawJsonObject = coinInfoJsonContainerDto.json
        if (rawJsonObject == null) return listCoinInfoDto
        val coinKeySet = rawJsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJsonObject = rawJsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJsonObject.keySet()
            for (currencyKey in currencyKeySet) {
                val coinPriceInfoJsonObject = currencyJsonObject.getAsJsonObject(currencyKey)
                val coinInfoDto = Gson().fromJson<CoinInfoDto>(coinPriceInfoJsonObject, CoinInfoDto::class.java)
                listCoinInfoDto.add(coinInfoDto)
            }

        }
        return listCoinInfoDto
    }

    fun mapNamesListToString(coinNamesListDto: CoinNamesListDto): String {
        return coinNamesListDto.names?.map { it.coinName?.name }?.joinToString(",") ?: ""
    }

    private fun getFullImageUrl(imageurl: String?): String {
        return BASE_IMAGE_URL + imageurl
    }

    private fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return  ""
        val timestampMillisecond = Timestamp((timestamp * 1000).toLong())
        val date = Date(timestampMillisecond.time)
        val pattern = "HH:mm:ss"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getDefault()
        return simpleDateFormat.format(date)
    }

    companion object {
        const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }

}