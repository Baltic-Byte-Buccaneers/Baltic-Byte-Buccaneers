package com.example.balticbytebuccaneers.util

inline fun <T1, T2, R> onNonNull(object1: T1?, object2: T2?, onNonNull: (T1, T2) -> R?): R? {

    object1?.let { nonNullObject1 ->
        object2?.let { nonNullObject2 ->
            return onNonNull(nonNullObject1, nonNullObject2)
        }
    } ?: run {
        return null
    }
}

inline fun <T1, T2, T3, R> onNonNull(object1: T1?, object2: T2?, object3: T3?, onNonNull: (T1, T2, T3) -> R?): R? {
    return if (null in listOf(object1, object2, object3)) {
        null
    } else {
        onNonNull(object1!!, object2!!, object3!!)
    }
}

fun main() {
    if (null in listOf<Any?>("object1", null, "object3")) {
        println("null")
    } else {
        println("Non null")
    }
}