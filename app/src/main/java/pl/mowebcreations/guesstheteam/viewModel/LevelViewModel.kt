package pl.mowebcreations.guesstheteam.viewModel

import android.app.Application
import android.media.MediaPlayer
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import pl.mowebcreations.guesstheteam.R
import pl.mowebcreations.guesstheteam.data.Level
import pl.mowebcreations.guesstheteam.data.Points
import pl.mowebcreations.guesstheteam.repository.LevelRepository
import pl.mowebcreations.guesstheteam.repository.PlayerRepository
import pl.mowebcreations.guesstheteam.repository.PointsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LevelViewModel(app: Application) : AndroidViewModel(app) {
    private val levelRepository = LevelRepository(app)
    private val playerRepository = PlayerRepository(app)
    private val pointsRepository = PointsRepository(app)

    private val wrongSound = MediaPlayer.create(app, R.raw.wrong)!!
    private val correctSound = MediaPlayer.create(app, R.raw.correct)!!

    val levelsFlow = levelRepository.getAllLevels()
    var totalPoints = pointsRepository.getTotalPoints()
    fun getLevelById(id: Long) = levelRepository.getLevelById(id)
    fun getLevelPlayers(levelId: Long) = levelRepository.getLevelPlayersById(levelId)
    fun getAllLevelsCount() = levelRepository.getEnabledLevelsCount()


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
        if (level.answer.lowercase() == answer.lowercase() || level.shortAnswer.lowercase() == answer.lowercase()) {
            viewModelScope.launch(Dispatchers.IO) {
                levelRepository.setLevelCompleted(level)
                playerRepository.setLevelPlayersNamesVisible(level)
                levelRepository.unBlockNextLevel()
                correctSound.start()
            }
        } else {
            wrongSound.start()
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
