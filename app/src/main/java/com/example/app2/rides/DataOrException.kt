package com.example.app2.rides

data class DataOrException<T1, T2, E : Exception?>(
    var data: T1? = null,
    var user: T2? = null,
    var e: E? = null
)