package com.genesis.feature_auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.genesis.core.navigation.NavigationProvider
import com.genesis.core.navigation.Routes
import com.genesis.feature_auth.ui.login.LoginScreen
import com.genesis.feature_auth.ui.register.RegisterScreen

class AuthNavigationProvider : NavigationProvider {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.composable(Routes.Login.route) {
            LoginScreen(navController = navController)
        }
        navGraphBuilder.composable(Routes.Register.route) {
            RegisterScreen(navController = navController)
        }
    }
}