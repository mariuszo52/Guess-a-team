package com.example.guesstheteam.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelDao {

    @Query("SELECT * from Level")
    fun getAllLevels(): Flow<List<Level>>

    @Query("SELECT * from Player where levelId = :levelId")
    fun getLevelPlayersById(levelId: Long): Flow<List<Player>>

    @Query("SELECT * from Level WHERE id=:id")
    fun getLevelById(id: Long): Flow<Level>
}
