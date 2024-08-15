package com.example.guesstheteam.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.guesstheteam.data.Points
import com.example.guesstheteam.repository.PointsRepository
import kotlinx.coroutines.flow.first

class PointsViewModel(app:Application):AndroidViewModel(app) {
    private val pointsRepository = PointsRepository(app)

    suspend fun updatePoints(pointsToAdd: Int){
        val totalPoints = pointsRepository.getTotalPoints().first()
        pointsRepository.updateTotalPoints(Points(1, totalPoints.plus(pointsToAdd)))
    }
}