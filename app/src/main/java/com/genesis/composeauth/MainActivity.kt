package com.genesis.composeauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.genesis.core.navigation.AppNavHost
import com.genesis.core.navigation.NavigationProvider
import com.genesis.core.ui.bottom_navigation.BottomNavItem
import com.genesis.core.ui.bottom_navigation.BottomNavigationBar
import com.genesis.core.ui.composables.NoInternetScreen
import com.genesis.core.ui.theme.ComposeAuthTheme
import com.genesis.core.utils.NetworkObserver
import com.genesis.feature_auth.navigation.AuthNavigationProvider
import com.genesis.feature_auth.viewmodel.AuthViewModel
import com.genesis.feature_cart.navigation.CartNavigationProvider
import com.genesis.feature_home.navigation.HomeNavigationProvider
import com.genesis.feature_profile.navigation.ProfileNavigationProvider
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    private lateinit var networkObserver: NetworkObserver

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkObserver = NetworkObserver(applicationContext)
        // Enable edge-to-edge display to draw behind the system bars
        enableEdgeToEdge()
        setContent {
            val isConnected by networkObserver.observe().collectAsState(initial = true)
            // Your custom theme, likely named ComposeAuthTheme based on project structure
            ComposeAuthTheme {
                if (isConnected) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route
                    val authViewModel: AuthViewModel = koinViewModel()

                    val bottomNavRoutes = setOf(
                        BottomNavItem.Home.route,
                        BottomNavItem.Profile.route,
                        BottomNavItem.Cart.route
                    )

                    Scaffold(
                        bottomBar = {
                            if (currentRoute in bottomNavRoutes) {
                                BottomNavigationBar(navController = navController) {
                                    authViewModel.logout()
                                    navController.navigate("login") {
                                        popUpTo(navController.graph.startDestinationRoute!!) { inclusive = true }
                                    }
                                }
                            }
                        }
                    ) { innerPadding ->
                        AppNavHost(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController,
                            navigationProviders = setOf<NavigationProvider>(
                                AuthNavigationProvider(),
                                HomeNavigationProvider(),
                                ProfileNavigationProvider(),
                                CartNavigationProvider()
                            )
                        )
                    }
                } else {
                    NoInternetScreen()
                }
            }
        }
    }
}
