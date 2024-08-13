package com.example.guesstheteam.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Level(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val answer: String,
    val shortAnswer: String,
    val isCompleted: Boolean,
    val league: String,
    val isLeagueShowed: Boolean,
    val isTeamNameShowed: Boolean,
    val isEnabled: Boolean
)