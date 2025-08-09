package com.example.fakestore.utils

sealed class State<out T> {
    data object Loading : State<Nothing>()
    data class Success<T>(val data: T) : State<T>()
    data class Error<String>(val message:String):State<String>()
}