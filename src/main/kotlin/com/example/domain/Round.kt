package com.example.domain

import kotlin.math.roundToLong

fun roundTwoDp(number: Double): String {
    val rounded = (number * 100).roundToLong().toString()
    return when(rounded.length) {
        1 -> "0.0$rounded"
        2 -> "0.$rounded"
        else -> rounded.dropLast(2) + "." + rounded.takeLast(2)
    }
}