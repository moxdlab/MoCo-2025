package io.moxd.mocohands_on.coroutine_exercises

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// TODO: what will be printed?
// TODO: what did I want it to print?
// TODO: how to achieve that (without altering the delays or calling order)?

fun main() {
    runBlocking {
        val parent = launch {
            launchChild(this@runBlocking)
            delay(50)
            println("return from launchChild")
        }
        delay(100)
        parent.cancel()
        println("Job cancelled")
    }
}

fun launchChild(scope: CoroutineScope) {
    val child = scope.launch {
        println("Starting doing things in child job")
        delay(1000)
        println("Hi, doing things in child job, on context: ${scope.coroutineContext}")
    }
}