package com.example.guesstheteam.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.guesstheteam.data.Level
import com.example.guesstheteam.repository.LevelRepository
import com.example.guesstheteam.repository.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LevelViewModel(app: Application) : AndroidViewModel(app) {
    private val levelRepository = LevelRepository(app)
    private val playerRepository = PlayerRepository(app)
    val levelsFlow = levelRepository.getAllLevels()
    fun getLevelById(id: Long) = levelRepository.getLevelById(id)
    fun getLevelPlayers(levelId: Long) = levelRepository.getLevelPlayersById(levelId)
    fun getAllLevelsCount() = levelRepository.getAllLevelsCount()
    suspend fun showLeagueName(level: Level) {
        return levelRepository.showLeagueName(level)
    }

    fun onCheckClick(level: Level, answer: String) {
        if (level.answer.lowercase() == answer.lowercase()) {
            viewModelScope.launch(Dispatchers.IO) {
                levelRepository.setLevelCompleted(level)
                playerRepository.setLevelPlayersNamesVisible(level)
                levelRepository.unBlockNextLevel()
            }
        }
    }

    suspend fun setTeamNameShowed(level: Level) {
        levelRepository.setTeamNameShowed(level)
    }

    suspend fun resetProgress() {
        try {
            levelRepository.setLevelProgressColumnsToFalse()
            levelRepository.setDefaultEnabledLevels()
            playerRepository.hideAllPlayers()
            withContext(Dispatchers.Main) {
                Toast.makeText(getApplication(), "Zresetowano postęp", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main){
                Toast.makeText(getApplication(), "Błąd podczas resetownia postępu", Toast.LENGTH_SHORT).show()

            }

        }

    }


}
