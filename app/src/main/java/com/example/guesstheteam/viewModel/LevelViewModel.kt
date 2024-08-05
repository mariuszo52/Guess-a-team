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
    private val _levelId = MutableStateFlow<Long?>(null)
    val levelId: StateFlow<Long?> get() = _levelId

    fun addLevel(level: Level){
        viewModelScope.launch {
            val id = levelRepository.addLevel(level)
            _levelId.value = id
        }
    }



}
