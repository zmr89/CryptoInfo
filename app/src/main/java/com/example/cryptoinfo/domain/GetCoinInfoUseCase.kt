package com.example.cryptoinfo.domain

import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(
    private val repository : CoinInfoRepository
    ) {
    operator fun invoke(fsym: String?) = repository.getCoinInfo(fsym)
}