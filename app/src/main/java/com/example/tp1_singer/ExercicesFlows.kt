package com.example.tp1_singer

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip

suspend fun main() {
    //Exo 1
    val flowEx1 = ex1()
    flowEx1.map { it * 3 }
        .filter { it % 2 == 0 }
        .collect { println(it) }

    val somme = flowEx1.fold(0) { acc, value -> acc + value }
    println("Somme : $somme")
}

fun ex1() : Flow<Int> {
    return flow {
        for (i in 1..3) {
            emit(i)
            delay(200L)
        }
    }
}

fun ex2() : Flow<Char> {
    return flowOf('a', 'b', 'c').onEach{delay(200)}
}