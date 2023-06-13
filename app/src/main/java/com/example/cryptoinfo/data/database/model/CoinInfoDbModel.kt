package com.example.cryptoinfo.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_price_list")
data class CoinInfoDbModel (
    val imageurl: String,
    val tosymbol: String?,
    @PrimaryKey
    val fromsymbol: String,
    val price: Double?,
    val lowday: Double?,
    val highday: Double?,
    val lastmarket: String?,
    val lastupdate: Long?
)