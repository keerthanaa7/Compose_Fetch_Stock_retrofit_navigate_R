package com.example.compose_fetch_stock_retrofit_navigate_r

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun PortfolioScreen(
    viewModel: PortfolioViewModelHilt,
    onStockClick: (String) -> Unit
) {
    // Collect the UI state from the ViewModel
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val state = uiState) {
            is PortfolioUiStateHilt.Loading -> {
                CircularProgressIndicator()
            }
            is PortfolioUiStateHilt.Success -> {
                StockList(stocks = state.stocks, onStockClick = onStockClick)
            }
            is PortfolioUiStateHilt.Error -> {
                Text(text = state.message, color = Color.Red, fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun StockList(stocks: List<Stock>, onStockClick: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(stocks, key = { it.ticker }) { stock ->
            StockItem(stock = stock, onStockClick = onStockClick)
            HorizontalDivider()
        }
    }
}

@Composable
fun StockItem(stock: Stock, onStockClick: (String) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
     /*   AsyncImage(model = "url", contentDescription =  null, modifier = Modifier.size(48.dp),
            placeholder = "", error = )*/


        Column(
            modifier = Modifier
                .fillMaxWidth()
                // Use the stock's 'ticker' for navigation
                .clickable { onStockClick(stock.ticker) }
                .padding(16.dp)
        ) {
            Text(text = stock.ticker, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = stock.name, fontSize = 16.sp, color = Color.Gray)
        }
    }

}

