package com.example.guesstheteam.repository

import android.content.Context
import com.example.guesstheteam.data.GuessDatabase
import com.example.guesstheteam.data.Level

class LevelRepository(context: Context) {
    private val levelDao = GuessDatabase.getDatabase(context).LevelDao()

    fun getAllLevels() = levelDao.getAllLevels()

    suspend fun addLevel(level: Level):Long{
        return levelDao.addLevel(level)
    }

}