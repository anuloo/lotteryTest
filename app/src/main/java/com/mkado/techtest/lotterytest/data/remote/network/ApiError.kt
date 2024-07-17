package com.mkado.techtest.lotterytest.data.remote.network

import kotlinx.serialization.Serializable


@Serializable
data class ApiError(
    val errors: Map<String, List<String>>? = null,
    val type: String? = null,
    val title: String? = null,
    val status: Int? = null,
    val detail: String? = null,
    val instance: String? = null
)