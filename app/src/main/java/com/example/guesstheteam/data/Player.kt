package com.example.guesstheteam.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Level::class,
        parentColumns = ["id"],
        childColumns = ["levelId"]
    )],
    indices = [Index(value = ["levelId"])]
)
data class Player(
    @PrimaryKey
    var id: Long,
    var name: String,
    var countryUrl: String,
    var isShowed: Boolean,
    var position: Position,

    var levelId: Long

)
