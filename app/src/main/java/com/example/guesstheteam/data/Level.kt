package com.example.guesstheteam.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Level(
    @PrimaryKey var id: Long,
    var answer: String,
    var shortAnswer: String,
    var isCompleted: Boolean,
    var league: String
)