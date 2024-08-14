package com.example.guesstheteam.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.guesstheteam.data.Level
import com.example.guesstheteam.data.Points
import com.example.guesstheteam.repository.LevelRepository
import com.example.guesstheteam.repository.PlayerRepository
import com.example.guesstheteam.repository.PointsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LevelViewModel(app: Application) : AndroidViewModel(app) {
    private val levelRepository = LevelRepository(app)
    private val playerRepository = PlayerRepository(app)
    private val pointsRepository = PointsRepository(app)

    val levelsFlow = levelRepository.getAllLevels()
    var totalPoints = pointsRepository.getTotalPoints()
    fun getLevelById(id: Long) = levelRepository.getLevelById(id)
    fun getLevelPlayers(levelId: Long) = levelRepository.getLevelPlayersById(levelId)
    fun getAllLevelsCount() = levelRepository.getAllLevelsCount()


    suspend fun showLeagueName(level: Level, totalPoints: Int) {
        try {
            if (totalPoints >= 20) {
                pointsRepository.updateTotalPoints(Points(1, totalPoints - 20))
                levelRepository.showLeagueName(level)
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        getApplication(),
                        "Nie masz wystarczającej ilosci punktów",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    getApplication(),
                    "Błąd podczas wykonywania operacji",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
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

    suspend fun setTeamNameShowed(level: Level, totalPoints: Int) {
        try {
            pointsRepository.updateTotalPoints(Points(1, totalPoints - 90))
            levelRepository.setTeamNameShowed(level)
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    getApplication(),
                    "Błąd podczas wykonywania operacji",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
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
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    getApplication(),
                    "Błąd podczas resetownia postępu",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }

    }


}
