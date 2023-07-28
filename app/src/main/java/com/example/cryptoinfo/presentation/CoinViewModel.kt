package com.example.cryptoinfo.presentation

import androidx.lifecycle.ViewModel
import com.example.cryptoinfo.domain.GetCoinInfoListUseCase
import com.example.cryptoinfo.domain.GetCoinInfoUseCase
import com.example.cryptoinfo.domain.LoadAndInsertDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadAndInsertDataUseCase: LoadAndInsertDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fsym: String) = getCoinInfoUseCase.invoke(fsym)

    init {
            loadAndInsertDataUseCase()
    }

}