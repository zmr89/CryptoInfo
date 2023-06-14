package com.example.cryptoinfo.domain

class LoadAndInsertDataUseCase(private val coinInfoRepository: CoinInfoRepository) {
    operator fun invoke() = coinInfoRepository.loadAndInsertData()
}