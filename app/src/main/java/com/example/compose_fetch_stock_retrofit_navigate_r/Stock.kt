package com.example.compose_fetch_stock_retrofit_navigate_r

import com.google.gson.annotations.SerializedName

data class Stock(
    val ticker: String,
    val name: String,
    val currency: String,
    @SerializedName("current_price_cents") val priceCents: Int,
    @SerializedName("current_price_timestamp") val timestamp: Long,
    val quantity: Int? // Nullable because it might not be present
)
