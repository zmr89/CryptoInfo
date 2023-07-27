package com.example.cryptoinfo.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoinfo.data.repository.CoinInfoRepositoryImpl
import com.example.cryptoinfo.domain.GetCoinInfoListUseCase
import com.example.cryptoinfo.domain.GetCoinInfoUseCase
import com.example.cryptoinfo.domain.LoadAndInsertDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadAndInsertDataUseCase: LoadAndInsertDataUseCase
) : ViewModel() {

//    private val repository = CoinInfoRepositoryImpl(application)
//    val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
//    val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
//    val loadAndInsertDataUseCase = LoadAndInsertDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fsym: String) = getCoinInfoUseCase.invoke(fsym)

    init {
            loadAndInsertDataUseCase()
    }

}