package com.example.guesstheteam.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Level::class, Player::class], version = 11, exportSchema = false)
abstract class GuessDatabase : RoomDatabase() {
    abstract fun LevelDao(): LevelDao
    abstract fun PlayerDao(): PlayerDao

    companion object {
        @Volatile
        private var INSTANCE: GuessDatabase? = null

        fun getDatabase(context: Context): GuessDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GuessDatabase::class.java,
                    "guessDatabase"
                )
                    .createFromAsset("guessDatabase.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}