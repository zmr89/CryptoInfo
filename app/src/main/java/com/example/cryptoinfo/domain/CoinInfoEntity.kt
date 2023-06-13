package com.example.cryptoinfo.domain

data class CoinInfoEntity(
    val imageurl: String,
    val tosymbol: String?,
    val fromsymbol: String,
    val price: Double?,
    val lowday: Double?,
    val highday: Double?,
    val lastmarket: String?,
    val lastupdate: String
)