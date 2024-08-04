package com.example.guesstheteam.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface PlayerDao {

    @Insert
    suspend fun addPlayer(player: Player)
}