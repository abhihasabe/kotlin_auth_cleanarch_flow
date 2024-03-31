package com.example.cleanarch.core.navigation

import androidx.navigation.navigation
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.cleanarch.feature.home.presenation.ui.Home
import com.example.cleanarch.feature.suppliers.presenation.ui.Supplier
import com.example.cleanarch.feature.attendance.presenation.ui.AddAttendance
import com.example.cleanarch.feature.suppliers.presenation.ui.SupplierTimeSheet
import com.example.cleanarch.feature.regularization.presenation.ui.Regularization
import com.example.cleanarch.feature.regularization.presenation.ui.AddRegularization

fun NavGraphBuilder.appGraph(navController: NavController) {
    navigation(startDestination = NavigationItem.Home.route, route = NavigationItem.App.route) {
        composable(route = NavigationItem.Home.route) {
            Home(navController = navController)
        }
        composable(route = NavigationItem.Regularization.route) {
            Regularization(navController = navController)
        }
        composable(route = NavigationItem.AddRegularization.route) {
            AddRegularization(navController = navController)
        }
        composable(route = NavigationItem.Suppliers.route) {
            Supplier(navController = navController)
        }
        composable(route = NavigationItem.SupplierTimeSheet.route) {
            SupplierTimeSheet(navController = navController)
        }
        composable(route = NavigationItem.AddAttendance.route) {
            AddAttendance(navController = navController)
        }
    }
}