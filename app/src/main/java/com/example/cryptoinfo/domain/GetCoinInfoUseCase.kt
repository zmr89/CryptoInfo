package com.example.cryptoinfo.domain

class GetCoinInfoUseCase(private val repository : CoinInfoRepository) {
    operator fun invoke(fsym: String?) = repository.getCoinInfo(fsym)
}