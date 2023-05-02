package com.example.cryptoinfo.domain.sumin

class GetCoinInfoListUseCase(private val repository : CoinInfoRepository) {
    operator fun invoke() = repository.getCoinPriceInfoList()
}