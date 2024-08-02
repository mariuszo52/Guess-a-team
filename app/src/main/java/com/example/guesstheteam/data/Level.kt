package com.example.guesstheteam.data

data class Level(
    private var id: Long,
    private var players: List<Player>,
    private var answer: String,
    private var isCompleted: Boolean,
    private var league: String

)
