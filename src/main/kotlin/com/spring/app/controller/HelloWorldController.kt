package com.spring.app.controller

import kotlinx.coroutines.*
import kotlinx.coroutines.slf4j.MDCContext
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("Rest/V1")
class HelloWorldController {

    @GetMapping("HelloWorld")
    suspend fun helloWorld() = coroutineScope {
        "Hello world"
    }

    @GetMapping("Nums")
    suspend fun getNums():List<Int> = coroutineScope {
        val res = arrayListOf<Int>()
        val jobs = arrayListOf<Job>()
        repeat(100000) {
            jobs.add(
                launch(Dispatchers.IO + MDCContext()) {
                    res.add(it)
                }
            )
        }
        jobs.joinAll()
        res
    }
}