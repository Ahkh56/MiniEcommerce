package com.genesis.core.ui.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Profile : BottomNavItem("profile", Icons.Default.AccountCircle, "Profile")
    object Cart : BottomNavItem("cart", Icons.Default.ShoppingCart, "Cart")
    object Logout : BottomNavItem("logout", Icons.Default.ExitToApp, "Logout")
}
