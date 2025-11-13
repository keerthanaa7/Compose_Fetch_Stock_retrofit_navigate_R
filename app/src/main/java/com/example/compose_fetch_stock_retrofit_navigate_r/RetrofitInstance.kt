package com.example.compose_fetch_stock_retrofit_navigate_r

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    val api: StockApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL) // This is now correctly used
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockApiService::class.java)
    }
}
