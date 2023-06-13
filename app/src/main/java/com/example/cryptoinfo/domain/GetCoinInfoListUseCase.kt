package com.example.cryptoinfo.domain

class GetCoinInfoListUseCase(private val repository : CoinInfoRepository) {
    operator fun invoke() = repository.getCoinInfoList()
}