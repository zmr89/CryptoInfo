package com.example.cryptoinfo.domain

class LoadAndInsertDataUseCase(private val coinInfoRepository: CoinInfoRepository) {
    suspend operator fun invoke() = coinInfoRepository.loadAndInsertData()
}