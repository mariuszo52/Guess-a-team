package com.example.guesstheteam.repository

import android.content.Context
import com.example.guesstheteam.data.GuessDatabase
import com.example.guesstheteam.data.Player

class PlayerRepository(context: Context) {
    private val playerDao = GuessDatabase.getDatabase(context).PlayerDao()

    suspend fun addPlayer(player: Player){
        return playerDao.addPlayer(player)
    }


}