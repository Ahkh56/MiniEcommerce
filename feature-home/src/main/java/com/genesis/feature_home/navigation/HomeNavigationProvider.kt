package com.genesis.feature_home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.genesis.core.navigation.NavigationProvider
import com.genesis.core.navigation.Routes
import com.genesis.feature_home.ui.CategoryScreen
import com.genesis.feature_home.ui.HomeScreen
import com.genesis.feature_home.ui.HomeViewModel
import org.koin.androidx.compose.koinViewModel

class HomeNavigationProvider : NavigationProvider {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.composable(Routes.Home.route) {
            HomeScreen(navController = navController)
        }
        navGraphBuilder.composable(
            route = "category/{categoryName}",
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
            val homeViewModel: HomeViewModel = koinViewModel()
            val products = homeViewModel.uiState.value.let {
                if (it is com.genesis.feature_home.ui.HomeUiState.Success) {
                    it.products.filter { product -> product.category == categoryName }
                } else {
                    emptyList()
                }
            }
            CategoryScreen(
                category = categoryName,
                products = products,
                navController = navController
            )
        }
    }
}