package pl.mowebcreations.guesstheteam.repository

import android.content.Context
import pl.mowebcreations.guesstheteam.data.GuessDatabase
import pl.mowebcreations.guesstheteam.data.Points


class PointsRepository(context: Context) {
    private val pointsDao = GuessDatabase.getDatabase(context).PointsDao()

    fun getTotalPoints() = pointsDao.getQuantityFromPointsWhereIdIs1()

    suspend fun updateTotalPoints(points: Points) {
        pointsDao.updatePoints(points)
    }
}