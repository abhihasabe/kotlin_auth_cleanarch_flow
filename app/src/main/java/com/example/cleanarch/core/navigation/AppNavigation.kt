package com.example.cleanarch.core.navigation

enum class Screen {
    Auth, App, Splash, Login, Register, ForgotPassword, Home, Regularization, AddRegularization, Suppliers, SupplierTimeSheet, AddAttendance
}

sealed class NavigationItem(val route: String) {
    data object Auth : NavigationItem(route = Screen.Auth.name)
    data object App : NavigationItem(route = Screen.App.name)
    data object Splash : NavigationItem(route = Screen.Splash.name)
    data object Login : NavigationItem(route = Screen.Login.name)
    data object Register : NavigationItem(route = Screen.Register.name)
    data object ForgotPassword : NavigationItem(route = Screen.ForgotPassword.name)
    data object Home : NavigationItem(route = Screen.Home.name)
    data object Regularization : NavigationItem(route = Screen.Regularization.name)
    data object AddRegularization : NavigationItem(route = Screen.AddRegularization.name)
    data object Suppliers : NavigationItem(route = Screen.Suppliers.name)
    data object SupplierTimeSheet : NavigationItem(route = Screen.SupplierTimeSheet.name)
    data object AddAttendance : NavigationItem(route = Screen.AddAttendance.name)
}