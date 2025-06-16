package io.moxd.mocohands_on.coroutine_exercises

import kotlinx.coroutines.*

// suspended vs non-suspended functions
// TODO: what will be printed when using doWork() vs. doWork(scope)?

suspend fun doWork() = coroutineScope {
    launch {
        delay(1000)
        println("Hello from launched coroutine")
    }
}

fun doWork(coroutineScope: CoroutineScope) {
    coroutineScope.launch {
        delay(1000)
        println("Hello from launched coroutine")
    }
}


fun main() {
    runBlocking {
//        doWork()
        doWork(this@runBlocking)

        println("finish")
    }
}




