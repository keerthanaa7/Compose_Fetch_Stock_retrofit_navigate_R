package com.example.compose_fetch_stock_retrofit_navigate_r

import androidx.activity.result.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface PortfolioUiStateHilt {
    data class Success(val stocks: List<Stock>) : PortfolioUiStateHilt
    data class Error(val message: String) : PortfolioUiStateHilt
    object Loading : PortfolioUiStateHilt
}

@HiltViewModel
class PortfolioViewModelHilt @Inject constructor(private val repository: StockRepositoryHilt) : ViewModel() {
    private val _uiState = MutableStateFlow<PortfolioUiStateHilt>(PortfolioUiStateHilt.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchPortfolio()
    }

    fun fetchPortfolio() {
        viewModelScope.launch {
            _uiState.value = PortfolioUiStateHilt.Loading
            try {
                val portfolio = repository.getPortfolio()
                if (portfolio.stocks.isEmpty()) {
                    _uiState.value = PortfolioUiStateHilt.Error("Your portfolio is empty.")
                } else {
                    _uiState.value = PortfolioUiStateHilt.Success(portfolio.stocks)
                }
            } catch (e: Exception) {
                _uiState.value = PortfolioUiStateHilt.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}
