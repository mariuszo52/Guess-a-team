package com.example.guesstheteam.data

import androidx.room.Dao
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

    @Query("UPDATE Level SET isLeagueShowed = 1 WHERE id = :levelId")
    suspend fun updateLeagueVisibility(levelId: Long)

    @Query("UPDATE Level SET isCompleted = 1 WHERE id = :levelId")
    suspend fun updateIsLevelCompleted(levelId: Long)

    @Query("SELECT COUNT(*) from Level")
    fun countAllLevels(): Flow<Long>

    @Query("UPDATE Level SET isTeamNameShowed = 1 WHERE id = :levelId")
    suspend fun updateIsTeamNameShowed(levelId: Long)

    @Query("UPDATE Level SET isEnabled = 1 WHERE id = (SELECT id FROM LEVEL WHERE isEnabled = 0 ORDER BY id ASC LIMIT 1)")
    suspend fun setNextLevelEnabled()

    @Query("UPDATE Level SET isTeamNameShowed = 0, isCompleted = 0, isLeagueShowed = 0 WHERE id IS NOT NULL ")
    suspend fun setLevelProgressColumnsToFalse()

    @Query("UPDATE LEVEL SET isEnabled = 0 WHERE id > 5")
    suspend fun updateIsLevelEnabledToFalseWhereIdIsGreaterThan5()

}
