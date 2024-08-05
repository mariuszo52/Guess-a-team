package com.example.guesstheteam.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.guesstheteam.data.Player
import com.example.guesstheteam.repository.PlayerRepository
import kotlinx.coroutines.launch

class PlayerViewModel(app: Application) : AndroidViewModel(app) {
    private val levelRepository = PlayerRepository(app)

    fun addPlayer(player: Player){
        viewModelScope.launch {
            levelRepository.addPlayer(player)
        }
    }



}
