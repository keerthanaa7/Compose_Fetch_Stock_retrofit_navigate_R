package com.example.compose_fetch_stock_retrofit_navigate_r

class StockRepository {
    suspend fun getPortfolio(): Portfolio = RetrofitInstance.api.getPortfolio()
}