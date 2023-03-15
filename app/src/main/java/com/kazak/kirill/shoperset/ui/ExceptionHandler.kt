package com.kazak.kirill.shoperset.ui

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler

fun provideExceptionHandler() =
    CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d("COROUTINES_ERROR", throwable.message.toString())
    }

