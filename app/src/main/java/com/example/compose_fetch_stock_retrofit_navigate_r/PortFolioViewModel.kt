package com.example.compose_fetch_stock_retrofit_navigate_r

import androidx.activity.result.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface PortfolioUiState {
    data class Success(val stocks: List<Stock>) : PortfolioUiState
    data class Error(val message: String) : PortfolioUiState
    object Loading : PortfolioUiState
}

class PortfolioViewModel(private val repository: StockRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<PortfolioUiState>(PortfolioUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchPortfolio()
    }

    fun fetchPortfolio() {
        viewModelScope.launch {
            _uiState.value = PortfolioUiState.Loading
            try {
                val portfolio = repository.getPortfolio()
                if (portfolio.stocks.isEmpty()) {
                    _uiState.value = PortfolioUiState.Error("Your portfolio is empty.")
                } else {
                    _uiState.value = PortfolioUiState.Success(portfolio.stocks)
                }
            } catch (e: Exception) {
                _uiState.value = PortfolioUiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}
