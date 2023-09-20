package com.example.naversearchcompose.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.naversearchcompose.local.room.dao.MemoDao
import com.example.naversearchcompose.local.room.entity.MemoEntity

@Database(
    entities = [
        MemoEntity::class
    ],
    version = 1,
    exportSchema = false
)

internal abstract class NaverSearchDataBase : RoomDatabase() {
    abstract val memoDao: MemoDao
}