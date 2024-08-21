package pl.mowebcreations.guesstheteam.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pl.mowebcreations.guesstheteam.data.Points
import pl.mowebcreations.guesstheteam.repository.PointsRepository
import kotlinx.coroutines.flow.first

class PointsViewModel(app:Application):AndroidViewModel(app) {
    private val pointsRepository = PointsRepository(app)

    suspend fun updatePoints(pointsToAdd: Int){
        val totalPoints = pointsRepository.getTotalPoints().first()
        pointsRepository.updateTotalPoints(Points(1, totalPoints.plus(pointsToAdd)))
    }
}