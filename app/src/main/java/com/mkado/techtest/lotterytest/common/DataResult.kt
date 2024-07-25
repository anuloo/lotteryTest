package com.mkado.techtest.lotterytest.common

sealed class DataResult<out SuccessType, out ErrorType> {
    data class Success<out SuccessType>(val data: SuccessType) : DataResult<SuccessType, Nothing>()
    data class Error<out ErrorType>(val message: String?, val code: Int, val error: ErrorType?) : DataResult<Nothing, ErrorType>()
}
