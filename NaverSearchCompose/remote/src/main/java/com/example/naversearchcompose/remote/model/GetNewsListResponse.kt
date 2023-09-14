package com.example.naversearchcompose.remote.model

import com.google.gson.annotations.SerializedName

internal data class GetNewsListResponse(
    @SerializedName("display")
    // page 사이즈
    val display: Int,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("start")
    val start: Int,
    @SerializedName("total")
    val total: Int
){
    data class Item(
        @SerializedName("description")
        val description: String,
        @SerializedName("link")
        val link: String,
        @SerializedName("originallink")
        val originalLink: String,
        @SerializedName("pubDate")
        val pubDate: String,
        @SerializedName("title")
        val title: String
    )
}
