package com.example.naversearchtest.util

import android.view.View
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

fun View.clicks() = callbackFlow {
    setOnClickListener {
        trySend(Unit)
    }

    awaitClose { setOnClickListener(null) }
}

fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> = flow {
    var lastEmissionTime = 0L
    collect{ upstream ->
        val currentTime = System.currentTimeMillis()
        val mayEmit = currentTime - lastEmissionTime > windowDuration
        if (mayEmit){
            lastEmissionTime = currentTime
            emit(upstream)
        }
    }
}

fun View.customThrottle(windowDuration: Long) = this.clicks().throttleFirst(windowDuration)