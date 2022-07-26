package com.example.composeweather.data

class DataOrException<T,Boolean,E: Exception>(
    val data: T? = null,
    var isLoading: Boolean? = null,
    val error: E? = null
)