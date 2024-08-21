package pl.mowebcreations.guesstheteam.repository

import android.content.Context
import pl.mowebcreations.guesstheteam.data.GuessDatabase
import pl.mowebcreations.guesstheteam.data.Level
import pl.mowebcreations.guesstheteam.data.Player

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

    suspend fun hideAllPlayers() = playerDao.setIsShowedToFalseForAllPlayers()


}