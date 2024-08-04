package com.example.guesstheteam.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.guesstheteam.data.Level
import com.example.guesstheteam.data.Player
import com.example.guesstheteam.data.Position
import com.example.guesstheteam.repository.LevelRepository
import com.example.guesstheteam.repository.PlayerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LevelViewModel(app: Application) : AndroidViewModel(app) {
    private val levelRepo = LevelRepository(app)
    private val playerRepo = PlayerRepository(app)


    var levels = levelRepo.getAllLevels()

    fun addNewLevelWithPlayers() {

        val level = Level(
            id = 1L,
            answer = "Manchester United",
            shortAnswer = "Man Utd",
            isCompleted = false,
            league = "Premier League"
        )

        val player = Player(
            id = 1L,                    // Unikalne ID gracza
            name = "Cristiano Ronaldo", // Imię gracza
            countryUrl = "https://example.com/portugal.png", // URL flagi kraju gracza
            isShowed = true,            // Status wyświetlenia gracza (true oznacza, że gracz jest pokazany)
            position = Position.SN, // Pozycja gracza (tutaj zakładamy, że masz enum Position z wartością FORWARD)
            levelId = 1L
        )
        CoroutineScope(Dispatchers.IO).launch { levelRepo.addLevel(level) }
        CoroutineScope(Dispatchers.IO).launch { playerRepo.addPlayer(player) }


    }
}