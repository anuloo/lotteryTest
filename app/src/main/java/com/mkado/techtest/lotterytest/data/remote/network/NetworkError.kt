package com.mkado.techtest.lotterytest.data.remote.network

sealed class NetworkError {
    data class SerializationError(val message: String?, val cause: Throwable?) : NetworkError()
    data class UnknownError(val message: String?, val cause: Throwable?) : NetworkError()
}
