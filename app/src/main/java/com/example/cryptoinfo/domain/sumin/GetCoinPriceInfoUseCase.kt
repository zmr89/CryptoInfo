package com.example.cryptoinfo.domain.sumin

class GetCoinPriceInfoUseCase(private val repository : CoinPriceInfoRepository) {
    operator fun invoke() = repository.getCoinPriceInfo()
}