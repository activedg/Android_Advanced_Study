package com.example.naversearchcompose.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.naversearchcompose.local.room.entity.MemoEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface MemoDao {
    @Query("SELECT * FROM memo ORDER BY id DESC")
    fun fetchList(): Flow<List<MemoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(memoList: List<MemoEntity>)
}