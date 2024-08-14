package com.example.guesstheteam.repository

import android.content.Context
import com.example.guesstheteam.data.GuessDatabase
import com.example.guesstheteam.data.Points


class PointsRepository(context: Context) {
    private val pointsDao = GuessDatabase.getDatabase(context).PointsDao()

    fun getTotalPoints() = pointsDao.getQuantityFromPointsWhereIdIs1()

    suspend fun updateTotalPoints(points: Points) {
        pointsDao.updatePoints(points)
    }
}