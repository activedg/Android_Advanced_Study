package com.example.naversearchcompose.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
data class MemoEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val content: String,
    val isRead: Boolean = false
)