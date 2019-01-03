package benchmark

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

//This class demonstrates how lightweight the coroutine is with comparision to thread.

fun main() {
    //runThreads(1000_000, 100)
    testTabs()
}

fun testTabs() {
    System.out.format( gCS('_',32))
    //System.out.format("| %32s|\n", gNS(it)).toString()
    //System.out.format("| %32s|\n", gNS(it)).toString()
    repeat(10) {
        System.out.format("|%32s|\n", gNS(it))
    }
}

fun gNS(count: Int): String {
    var string = ""
    for (i in 0..count) {
        string += i
    }
    return string
}

fun gCS(char: Char, count: Int): String {
    var string = ""
    for (i in 0..count) {
        string += char
    }
    return string
}


fun runThreads(times: Int, duration: Long) {
    val startTime = System.currentTimeMillis()
    println("Start time of $times Threads with $duration ms delay")

    val t = thread {
        repeat(times) {
            thread {
                Thread.sleep(duration)
            }
        }
    }
    t.join()
    println("End Time of $times Threads with $duration ms delay->  ${(System.currentTimeMillis() - startTime) / 1000} seconds")
}

fun runCoroutines(times: Int, duration: Long) = runBlocking {
    val startTime = System.currentTimeMillis()
    println("Start time of $times Coroutine with $duration ms delay")
    coroutineScope {
        repeat(times) {
            launch {
                //Launches another coroutine inside current coroutine scope
                delay(duration)
                print("World !")
            }
        }
    }
    println("End Time of $times Coroutine with $duration ms delay->  ${(System.currentTimeMillis() - startTime) / 1000} seconds")
}


/**
 * ___________________________________________________________________________
 * Observations Thread
 * __________________________________________________________________________
 * Thread counts        \    duration  (ms)     \   Result                  \
 * _____________________\_______________________\___________________________\
 * 1K                   \      100              \                           \
 * 1K                   \     1000              \                           \
 * 1K                   \     5000              \                           \
 * 1K                   \    10000              \                           \
 * 1K                   \      100              \                           \
 * 1K                   \     1000              \                           \
 * 1K                   \     5000              \                           \
 * 1K                   \    10000              \                           \
 * 1K                   \      100              \                           \
 * 1K                   \     1000              \                           \
 * 1K                   \     5000              \                           \
 * 1K                   \    10000              \                           \
 * 1K                   \      100              \                           \
 * 1K                   \     1000              \                           \
 * 1K                   \     5000              \                           \
 * 1K                   \    10000              \                           \
 * 1K                   \      100              \                           \
 * 1K                   \     1000              \                           \
 * 1K                   \     5000              \                           \
 * 1K                   \    10000              \                           \
 * 1K                   \      100              \                           \
 * 1K                   \     1000              \                           \
 * 1K                   \     5000              \                           \
 * 1K                   \    10000              \                           \
 */
/**
 * ___________________________________________________________________________
 * Observations Coroutine
 * __________________________________________________________________________
 * Coroutine counts     \    duration           \   Result                  \
 * _____________________\_______________________\___________________________\
 * 100K                 \                       \                           \
 * 100K                 \                       \                           \
 * 100K                 \                       \                           \
 * 100K                 \                       \                           \
 * 100K                 \                       \                           \
 * 100K                 \                       \                           \
 */