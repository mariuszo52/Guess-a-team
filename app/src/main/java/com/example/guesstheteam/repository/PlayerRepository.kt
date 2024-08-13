package com.example.guesstheteam.repository

import android.content.Context
import com.example.guesstheteam.data.GuessDatabase
import com.example.guesstheteam.data.Level
import com.example.guesstheteam.data.Player

class PlayerRepository(context: Context) {
    private val playerDao = GuessDatabase.getDatabase(context).PlayerDao()

    suspend fun addPlayer(player: Player){
        return playerDao.addPlayer(player)
    }

    suspend fun setLevelPlayersNamesVisible(level: Level){
        playerDao.updateLevelPlayersAreShowed(level.id)
    }

    suspend fun setPlayerShowedById(player: Player){
        playerDao.setPlayerShowedById(player.id)
    }
}