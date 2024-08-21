package pl.mowebcreations.guesstheteam.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Points(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val quantity: Int)
