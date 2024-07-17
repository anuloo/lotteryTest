package com.mkado.techtest.lotterytest.data.remote.network

sealed class NetworkResult<out SuccessType, out ErrorType> {

    data class Success<out SuccessType>(val data: SuccessType) : NetworkResult<SuccessType, Nothing>()

    data class Error<out ErrorType>(val message: String?, val code: Int, val error: ErrorType?) :
        NetworkResult<Nothing, ErrorType>()

    object Exception : NetworkResult<Nothing, Nothing>()

    object SerializationException : NetworkResult<Nothing, Nothing>()
}