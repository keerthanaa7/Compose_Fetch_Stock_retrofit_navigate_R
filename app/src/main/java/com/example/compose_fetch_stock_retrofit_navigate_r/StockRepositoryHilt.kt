package com.example.compose_fetch_stock_retrofit_navigate_r

import retrofit2.Retrofit
import javax.inject.Inject

class StockRepositoryHilt @Inject constructor(private val apiService: StockApiService) {
    suspend fun getPortfolio(): Portfolio{
        return apiService.getPortfolio()
    }
}