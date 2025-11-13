package com.example.compose_fetch_stock_retrofit_navigate_r

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query

@Dao
interface StockDao {

    @Query("SELECT * FROM StockTable")
    suspend fun getAllstocks()

    @Insert(onConflict = REPLACE)
    suspend fun insertAllStocks(stocklist:List<Stock>)

    @Query("DELETE FROM StockTable")
    suspend fun clearAll()
}