package com.example.guesstheteam.viewModel

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.guesstheteam.data.Level
import com.example.guesstheteam.data.Player
import com.example.guesstheteam.repository.LevelRepository
import com.example.guesstheteam.repository.PlayerRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Collections
import java.util.Random

class PlayerViewModel(app: Application) : AndroidViewModel(app) {
    private val playerRepository = PlayerRepository(app)
    private val levelRepository = LevelRepository(app)

    fun addPlayer(player: Player) {
        viewModelScope.launch {
            playerRepository.addPlayer(player)
        }
    }

    suspend fun showPlayer(level: Level) {
        val players = levelRepository.getLevelPlayersById(level.id).first()
        println("wykonuje sie showPlayer")
        val showedPlayersCount = players.filter(Player::isShowed).size
        if (showedPlayersCount < 11) {
            var playerToShow: Player
            do {
                println("wykonuje sie pętla")
                val random = Random()
                playerToShow = players[random.nextInt(players.size)]
            } while (playerToShow.isShowed)
            playerRepository.setPlayerShowedById(playerToShow)
        }

    }


}
