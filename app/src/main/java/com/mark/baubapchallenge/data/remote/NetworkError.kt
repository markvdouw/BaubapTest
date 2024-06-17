package com.mark.baubapchallenge.data.remote

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkError(message: String) : Throwable(message)

//TODO Auth errors, token expiration and other will be added at http client interceptor level
fun Exception.toNetworkError(): NetworkError = when (this) {
    is SocketTimeoutException -> NetworkError("connection error!")
    is ConnectException -> NetworkError("no internet access!")
    is UnknownHostException -> NetworkError("no internet access!")
    else -> NetworkError(this.message ?: "internal error")
}