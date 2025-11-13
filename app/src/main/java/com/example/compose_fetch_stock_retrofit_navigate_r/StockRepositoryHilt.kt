package com.example.compose_fetch_stock_retrofit_navigate_r

import retrofit2.Retrofit
import javax.inject.Inject

class StockRepositoryHilt @Inject constructor() {
    suspend fun getPortfolio(): Portfolio{
        return RetrofitInstance.api.getPortfolio()
    }
}