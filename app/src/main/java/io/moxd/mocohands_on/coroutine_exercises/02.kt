package io.moxd.mocohands_on.coroutine_exercises

import kotlinx.coroutines.*

// TODO: are the coroutines running in pseudo or true parallelism?
suspend fun run() {
    coroutineScope {
        launch {
            delay(10)
            println("Hello from ${Thread.currentThread()}")
        }
        launch {
            delay(10)
            println("Hello from ${Thread.currentThread()}")
        }
        launch {
            delay(10)
            println("Hello from ${Thread.currentThread()}")
        }
    }
}
