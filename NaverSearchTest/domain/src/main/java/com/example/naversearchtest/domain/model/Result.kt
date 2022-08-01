package com.example.naversearchtest.domain.model

data class Result(
    val `data`: List<Data>,
    val keywords: List<String>,
    val title: String
)