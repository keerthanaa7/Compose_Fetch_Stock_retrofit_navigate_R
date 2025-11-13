package com.example.compose_fetch_stock_retrofit_navigate_r

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "StockTable")
data class PortFolioEntity(
    @PrimaryKey
    val ticker: String,
    val name: String,
    val currency: String,
    @SerializedName("current_price_cents") val priceCents: Int,
    @SerializedName("current_price_timestamp") val timestamp: Long,
    val quantity: Int?
)
