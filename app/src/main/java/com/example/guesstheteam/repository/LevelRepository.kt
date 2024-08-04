package com.example.guesstheteam.repository

import com.example.guesstheteam.data.Level
import com.example.guesstheteam.data.LevelDao
import kotlinx.coroutines.flow.Flow

class LevelRepository(private val levelDao: LevelDao){

    fun getAllLevels(): Flow<List<Level>>{
        return levelDao.getAllLevels()
    }
}