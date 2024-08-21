package pl.mowebcreations.guesstheteam.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import pl.mowebcreations.guesstheteam.data.Level
import pl.mowebcreations.guesstheteam.data.Player
import pl.mowebcreations.guesstheteam.data.Points
import pl.mowebcreations.guesstheteam.repository.LevelRepository
import pl.mowebcreations.guesstheteam.repository.PlayerRepository
import pl.mowebcreations.guesstheteam.repository.PointsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.util.Random

class PlayerViewModel(app: Application) : AndroidViewModel(app) {
    private val playerRepository = PlayerRepository(app)
    private val levelRepository = LevelRepository(app)
    private val pointsRepository = PointsRepository(app)

    suspend fun showPlayer(level: Level, totalPoints: Int) {
        try {
            if (totalPoints >= 10) {
                pointsRepository.updateTotalPoints(Points(1, totalPoints - 10))
                val players = levelRepository.getLevelPlayersById(level.id).first()
                val showedPlayersCount = players.filter(Player::isShowed).size
                if (showedPlayersCount < 11) {
                    var playerToShow: Player
                    do {
                        val random = Random()
                        playerToShow = players[random.nextInt(players.size)]
                    } while (playerToShow.isShowed)
                    playerRepository.setPlayerShowedById(playerToShow)
                }
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
}
