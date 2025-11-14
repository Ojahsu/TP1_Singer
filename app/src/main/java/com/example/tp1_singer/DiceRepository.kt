package com.example.tp1_singer

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

class DiceRepository {

    fun generate(n: Int, speed: Long): Flow<Int> {
        return flow {
            for (i in 1..n) {
                emit((1..6).random())
                delay(speed)
            }
        }
    }
}