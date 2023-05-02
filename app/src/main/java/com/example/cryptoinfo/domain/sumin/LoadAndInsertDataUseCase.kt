package com.example.cryptoinfo.domain.sumin

class LoadAndInsertDataUseCase(private val coinInfoRepository: CoinInfoRepository) {
    suspend operator fun invoke() = coinInfoRepository.loadAndInsertData()
}