package com.example.compose_fetch_stock_retrofit_navigate_r

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PortfolioViewModelFactory(private val repository: StockRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PortfolioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PortfolioViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}