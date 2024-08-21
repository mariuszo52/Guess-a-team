package pl.mowebcreations.guesstheteam.data

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
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val countryUrl: String,
    val isShowed: Boolean,
    val position: Position,
    val levelId: Long

)
