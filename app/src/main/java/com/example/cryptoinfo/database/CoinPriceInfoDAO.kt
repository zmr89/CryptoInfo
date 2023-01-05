package com.example.cryptoinfo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.cryptoinfo.pojo.CoinPriceInfo

@Dao
interface CoinPriceInfoDAO {

    @Query("SELECT * FROM full_price_list ORDER BY lastupdate")
    fun getListCoinPriceInfo(): LiveData<List<CoinPriceInfo>>

    @Query("SELECT * FROM full_price_list WHERE fromsymbol == :fsyms LIMIT 1")
    fun getCoinPriceInfo(fsyms: String?): LiveData<CoinPriceInfo>

    @Insert(onConflict = REPLACE)
    fun insertListCoinPriceInfo(list: List<CoinPriceInfo>)
}