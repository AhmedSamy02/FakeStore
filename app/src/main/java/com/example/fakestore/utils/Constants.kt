package com.example.fakestore.utils

import android.util.Log
import com.example.fakestore.data.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.util.concurrent.TimeoutException

const val NUM_TRIES = 3
fun <T> callApiWithRetry(apiCall: suspend () -> Response<T>): Flow<State<T>> = flow {
    emit(State.Loading)
    var i = 0
    while (i != NUM_TRIES) {
        try {
            val response = apiCall()
            if (!response.isSuccessful) if (response.message().isNullOrBlank()) {
                emit(
                    State.Error(
                        errorMessageFromStatusCode(response.code())
                    )
                )
            } else {
                emit(
                    State.Error(response.message())

                )
            }
            else emit(State.Success<T>(response.body()!!))
            return@flow
        } catch (e: HttpException) {
            emit(State.Error(errorMessageFromStatusCode(e.response()!!.code())))
            return@flow
        } catch (e: TimeoutException) {
            i++
            continue
        } catch (e: Exception) {

            emit(
                State.Error(
                    e.localizedMessage
                )
            )
            return@flow
        }
    }
    emit(
        State.Error("Connection timed out please check your internet connection and try again")
    )
}

fun errorMessageFromStatusCode(code: Int?) = when (code) {
    400 -> "Bad Request"
    401 -> "User is Unauthorized"
    402 -> "Payment is Required"
    403 -> "Request Forbidden"
    404 -> "Not Found"
    405 -> "Method Not Allowed"
    406 -> "Not Acceptable"
    407 -> "Proxy Authentication Required"
    408 -> "Request Timeout"
    409 -> "Conflict"
    410 -> "Gone"
    411 -> "Length Required"
    412 -> "Precondition Failed"
    413 -> "Payload Too Large"
    414 -> "Uri Too Long"
    415 -> "Unsupported Media Type"
    416 -> "Range Not Satisfiable"
    417 -> "Expectation Failed"
    418 -> "IAmATeapot"
    421 -> "Misdirected Request"
    422 -> "Unprocessable Entity"
    423 -> "Locked"
    424 -> "Failed Dependency"
    426 -> "Upgrade Required"
    428 -> "Precondition Required"
    429 -> "Too Many Requests"
    431 -> "Request Header Fields Too Large"
    451 -> "Unavailable For Legal Reasons"

    500 -> "Internal Server Error"
    501 -> "Not Implemented"
    502 -> "Bad Gateway"
    503 -> "Service Unavailable"
    504 -> "Gateway Timeout"
    505 -> "Http Version Not Supported"
    506 -> "Variant Also Negotiates"
    507 -> "Insufficient Storage"
    508 -> "Loop Detected"
    510 -> "Not Extended"
    511 -> "Network Authentication Required"
    else -> "Unknown Network error"
}