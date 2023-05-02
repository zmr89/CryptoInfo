package com.example.cryptoinfo.domain.sumin

class GetCoinInfoUseCase(private val repository : CoinInfoRepository) {
    operator fun invoke(fsym: String?) = repository.getCoinPriceInfo(fsym)
}