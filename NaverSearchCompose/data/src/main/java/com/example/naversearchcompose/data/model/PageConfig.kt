package com.example.naversearchcompose.data.model

data class PageConfig(
    val start: Int = 1,
    val page: Int = 20,
    val total: Int = Int.MAX_VALUE
){
    val isLast = start + page > total
}