package com.example.guesstheteam.data

data class Level(
    var id: Long,
    var players: List<Player>,
    var answer: String,
    var shortAnswer: String,
    var isCompleted: Boolean,
    var league: String
)