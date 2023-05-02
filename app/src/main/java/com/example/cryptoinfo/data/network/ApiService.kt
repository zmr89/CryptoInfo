package com.example.cryptoinfo.data.network

import com.example.cryptoinfo.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptoinfo.data.network.model.CoinNamesListDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    suspend fun getTopList(@Query(QUERY_PARAM_LIMIT) limit: Int = 10,
                   @Query(QUERY_PARAM_TO_SYMBOL) tsym: String = CURRENCY,
                   @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY
    ): CoinNamesListDto

    @GET("pricemultifull")
    suspend fun getCoinPriceInfoRawData(@Query(QUERY_PARAM_FROM_SYMBOLS) fsyms: String,
                                @Query(QUERY_PARAM_TO_SYMBOLS) tsyms: String = CURRENCY,
                                @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY
    ): CoinInfoJsonContainerDto


    companion object{
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"

        private const val CURRENCY = "USD"
        private const val API_KEY = "4812b484ce6d1a6fbee193f3c8c1be4ab4802f97e49927beb0220a01ca41ce0a"
    }


}