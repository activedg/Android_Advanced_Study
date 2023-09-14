package com.example.naversearchcompose.data.model

data class PageConfig(
    val start: Int = 0,
    val display: Int = 10,
    val total: Int = Int.MAX_VALUE
)