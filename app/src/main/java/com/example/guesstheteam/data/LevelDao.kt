package com.example.guesstheteam.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelDao {

    @Query("SELECT * from Level")
    fun getAllLevels(): Flow<List<Level>>

    @Insert
    suspend fun addLevel(level: Level)
}
