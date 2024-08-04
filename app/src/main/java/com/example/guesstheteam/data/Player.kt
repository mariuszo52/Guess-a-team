package com.example.guesstheteam.data


data class Player(
    var id: Long,
    var name: String,
    var countryUrl: String,
    var isShowed: Boolean,
    var Position: Position

)
