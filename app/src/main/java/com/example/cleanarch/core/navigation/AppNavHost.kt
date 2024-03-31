package com.example.cleanarch.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Auth.route,
) {
    NavHost(
        modifier = modifier, navController = navController, startDestination = startDestination
    ) {
        authGraph(navController)
        appGraph(navController)
    }
}