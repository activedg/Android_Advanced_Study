package com.example.naversearchtest.domain.model

data class SearchResponse(
    val endDate: String,
    val results: List<Result>,
    val startDate: String,
    val timeUnit: String
)