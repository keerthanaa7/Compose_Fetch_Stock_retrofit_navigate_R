package com.example.compose_fetch_stock_retrofit_navigate_r

import retrofit2.http.GET
import retrofit2.http.Query

interface StockApiService {
    @GET(Constants.PORTFOLIO_ENDPOINT)
    suspend fun getPortfolio(): Portfolio
}