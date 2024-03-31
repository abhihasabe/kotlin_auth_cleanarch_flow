package com.example.cleanarch.core.navigation

import Register
import androidx.navigation.navigation
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.cleanarch.feature.splash.presentation.ui.Splash
import com.example.cleanarch.feature.auth.presenation.screen.Login
import com.example.cleanarch.feature.auth.presentation.ui.ForgotPassword

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(
        startDestination = NavigationItem.Splash.route, route = NavigationItem.Auth.route
    ) {
        composable(route = NavigationItem.Splash.route) {
            Splash(navController = navController)
        }
        composable(route = NavigationItem.Login.route) {
            Login(navController = navController)
        }
        composable(route = NavigationItem.Register.route) {
            Register(navController = navController)
        }
        composable(route = NavigationItem.ForgotPassword.route) {
            ForgotPassword(navController = navController)
        }
    }
}