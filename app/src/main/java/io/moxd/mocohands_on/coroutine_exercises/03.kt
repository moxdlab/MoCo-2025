package io.moxd.mocohands_on.coroutine_exercises

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

// TODO: what times do you expect?

fun main() {
    trueParallelizm()
//    quasiParallelizm()
}

fun trueParallelizm() {
    println("true")
    runBlocking(Dispatchers.Default) {
        addAsyncs()
    }
}

fun quasiParallelizm() {
    println("quasi")
    runBlocking {
        addAsyncs()
    }
}

suspend fun addAsyncs() {
    coroutineScope {
        val millis = measureTimeMillis {
            val a = async {
                delay(500)
                100
            }
            val b = async {
                delay(500)
                20
            }
            println("a+b = ${a.await() + b.await()}")
        }
        println("$millis ms")
    }
}