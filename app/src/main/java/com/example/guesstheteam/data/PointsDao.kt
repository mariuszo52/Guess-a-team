package com.example.guesstheteam.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PointsDao{

    @Query("SELECT quantity FROM Points WHERE id = 1")
    fun getQuantityFromPointsWhereIdIs1() : Flow<Int>

    @Update
    suspend fun updatePoints(points: Points)


}