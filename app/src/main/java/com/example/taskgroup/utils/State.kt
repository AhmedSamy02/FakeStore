package com.example.taskgroup.utils

sealed class State<out T> {
    data object Loading : State<Nothing>()
    data class Success<T>(val data: T) : State<T>()
    data class Error(var message: String?) :
        State<Nothing>() {
        init {
            if (message == null)
                message = "Unexpected Error please contact service provider"
        }
    }
}