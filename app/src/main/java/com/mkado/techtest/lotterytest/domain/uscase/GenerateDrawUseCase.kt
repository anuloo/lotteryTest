package com.mkado.techtest.lotterytest.domain.uscase

import com.mkado.techtest.lotterytest.domain.repository.LotteryRepository

interface DrawUseCase{
    fun execute(): List<Int>
}

class GenerateDrawUseCase(private val repository: LotteryRepository):DrawUseCase {
    override fun execute(): List<Int> {
        return repository.generateRandomNumbers()
    }
}