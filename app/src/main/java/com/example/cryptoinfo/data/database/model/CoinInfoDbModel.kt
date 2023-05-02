package com.example.cryptoinfo.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptoinfo.data.network.ApiFactory.BASE_IMAGE_URL
import com.example.cryptoinfo.utils.convertTimestampToTime
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinInfoDbModel (
    val imageurl: String?,
    val tosymbol: String?,
    @PrimaryKey
    val fromsymbol: String,
    val price: Double?,
    val lowday: Double?,
    val highday: Double?,
    val lastmarket: String?,
    val lastupdate: Long?
)