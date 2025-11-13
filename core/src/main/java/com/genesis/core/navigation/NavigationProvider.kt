package com.genesis.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface NavigationProvider {
    fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder)
}