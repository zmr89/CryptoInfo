package com.example.cryptoinfo.domain

import javax.inject.Inject

class LoadAndInsertDataUseCase @Inject constructor(
    private val coinInfoRepository: CoinInfoRepository
    ) {
    operator fun invoke() = coinInfoRepository.loadAndInsertData()
}