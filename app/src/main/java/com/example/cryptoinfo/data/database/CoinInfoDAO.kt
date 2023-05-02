package com.example.cryptoinfo.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.cryptoinfo.data.database.model.CoinInfoDbModel

@Dao
interface CoinInfoDAO {

    @Query("SELECT * FROM full_price_list ORDER BY lastupdate DESC")
    fun getListAllCoinPriceInfo(): LiveData<List<CoinInfoDbModel>>

    @Query("SELECT * FROM full_price_list WHERE fromsymbol == :fsym LIMIT 1")
    fun getCoinPriceInfo(fsym: String?): LiveData<CoinInfoDbModel>

    @Insert(onConflict = REPLACE)
    fun insertListCoinInfo(list: List<CoinInfoDbModel>)
}