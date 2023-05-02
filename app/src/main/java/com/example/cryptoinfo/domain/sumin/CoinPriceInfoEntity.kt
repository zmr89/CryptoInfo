package com.example.cryptoinfo.domain.sumin

data class CoinPriceInfoEntity(
    val imageurl: String?,
    val tosymbol: String?,
    val fromsymbol: String,
    val price: Double?,
    val lowday: Double?,
    val highday: Double?,
    val lastmarket: String?,
    val lastupdate: Long?
)