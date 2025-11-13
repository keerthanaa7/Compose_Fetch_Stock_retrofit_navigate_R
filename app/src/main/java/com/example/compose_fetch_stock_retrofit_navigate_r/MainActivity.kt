package com.example.compose_fetch_stock_retrofit_navigate_r

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose_fetch_stock_retrofit_navigate_r.ui.theme.Compose_Fetch_Stock_retrofit_navigate_RTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
   /* private val repository by lazy {
        StockRepository()
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_Fetch_Stock_retrofit_navigate_RTheme {
                StockApp()
            }
        }
    }
}

@Composable
fun StockApp() {
    val navController = rememberNavController()

    // Create a ViewModel instance using your existing factory.
    // This correctly provides the repository to the ViewModel.
  /*  val portfolioViewModel: PortfolioViewModel = viewModel(
        factory = PortfolioViewModelFactory(repository)
    )*/



    // NavHost manages the navigation between your composable screens.
    NavHost(navController = navController, startDestination = "portfolio") {

        // Route for the Portfolio (Stock List) Screen
        composable("portfolio") {
            PortfolioScreen(
                viewModel = hiltViewModel(),
                onStockClick = { stock ->
                    // Navigate to the detail screen, passing the clicked stock's ticker.
                    navController.navigate("stockDetail/$stock")
                }
            )
        }

        // Route for the Stock Detail Screen
        composable(
            route = "stockDetail/{stock}",
            arguments = listOf(navArgument("stock") { type = NavType.StringType })
        ) { backStackEntry ->
            // Retrieve the stock ticker from the navigation arguments.
            val stock = backStackEntry.arguments?.getString("stock")
            StockDetailScreen(stock = stock)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose_Fetch_Stock_retrofit_navigate_RTheme {
        Greeting("Android")
    }
}