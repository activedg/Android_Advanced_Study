package com.example.naversearchtest.domain.model

data class SearchData(
    val startDate: String,
    val endDate: String,
    val timeUnit: String,
    val keywordGroups: List<KeywordGroup>
)