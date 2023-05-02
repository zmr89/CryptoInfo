package com.example.cryptoinfo.domain.sumin

class GetCoinPriceInfoListUseCase(private val repository : CoinPriceInfoRepository) {
    operator fun invoke() = repository.getCoinPriceInfoList()
}