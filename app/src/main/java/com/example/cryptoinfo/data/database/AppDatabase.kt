package com.example.cryptoinfo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptoinfo.data.database.model.CoinInfoDbModel

@Database(entities = [CoinInfoDbModel::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    companion object{
        private const val DATABASE_NAME = "crypto.db"
        private var db: AppDatabase? = null
        private val LOCK = Any()
        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration().build()
                db = instance
                return instance
            }
        }
    }

    abstract fun getDAO(): CoinInfoDAO

}