package com.example.guesstheteam.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PlayerDao {

    @Insert
    suspend fun addPlayer(player: Player)


}