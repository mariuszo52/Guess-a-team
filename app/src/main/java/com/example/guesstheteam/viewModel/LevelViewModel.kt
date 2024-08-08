package com.example.guesstheteam.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.guesstheteam.data.Level
import com.example.guesstheteam.repository.LevelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LevelViewModel(app: Application) : AndroidViewModel(app) {
    private val levelRepository = LevelRepository(app)
    val levelsFlow = levelRepository.getAllLevels()
    fun getLevelById(id: Long) = levelRepository.getLevelById(id)
    fun getLevelPlayers(levelId: Long) = levelRepository.getLevelPlayersById(levelId)
    suspend fun showLeagueName(level: Level) {
        return levelRepository.showLeagueName(level)
    }


}
