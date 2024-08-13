package com.example.guesstheteam.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.guesstheteam.data.Level
import com.example.guesstheteam.repository.LevelRepository
import com.example.guesstheteam.repository.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LevelViewModel(app: Application) : AndroidViewModel(app) {
    private val levelRepository = LevelRepository(app)
    private val playerRepository = PlayerRepository(app)
    val levelsFlow = levelRepository.getAllLevels()
    fun getLevelById(id: Long) = levelRepository.getLevelById(id)
    fun getLevelPlayers(levelId: Long) = levelRepository.getLevelPlayersById(levelId)
    fun getAllLevelsCount() = levelRepository.getAllLevelsCount()
    suspend fun showLeagueName(level: Level) {
        return levelRepository.showLeagueName(level)
    }

    fun onCheckClick(level: Level, answer: String){
        if (level.answer.lowercase() == answer.lowercase()) {
            viewModelScope.launch(Dispatchers.IO) {
                levelRepository.setLevelCompleted(level)
                playerRepository.setLevelPlayersNamesVisible(level)
            }
        }
    }

    suspend fun setTeamNameShowed(level: Level){
        levelRepository.setTeamNameShowed(level)
    }


}
