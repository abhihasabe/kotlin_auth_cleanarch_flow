package com.example.cleanarch.core.util

sealed class Results<out R> {
    data class Success<out T>(val data: T) : Results<T>()
    data class Loading<out T>(val loadingState: Boolean) : Results<T>()
    data class Error(val message: String, val code: Int) : Results<Nothing>()
}