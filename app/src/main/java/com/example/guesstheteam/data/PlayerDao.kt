package com.example.guesstheteam.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PlayerDao {

    @Insert
    suspend fun addPlayer(player: Player)

    @Query("UPDATE Player SET isShowed = 1 where levelId = :levelId")
    suspend fun updateLevelPlayersAreShowed(levelId: Long)

    @Query("UPDATE Player SET isShowed = 1 where id = :id")
    suspend fun setPlayerShowedById(id: Long)
}