package io.moxd.mocohands_on.coroutine_exercises

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TODO: what will be printed?
fun main() {
    val cs = CoroutineScope(Dispatchers.Default)

    cs.launch {
        delay(1000)
        println("Hello World")
    }

    println("end")
}