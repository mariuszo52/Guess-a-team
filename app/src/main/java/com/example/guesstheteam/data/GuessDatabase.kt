package com.example.guesstheteam.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Level::class, Player::class], version = 1, exportSchema = false)
abstract class GuessDatabase: RoomDatabase() {
    abstract fun LevelDao(): LevelDao

    companion object {
        @Volatile
        private var INSTANCE: GuessDatabase? = null

        fun getDatabase(context: Context): GuessDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GuessDatabase::class.java,
                    "my_database"
                ).createFromAsset("prepopulate.sql")  // Wskazanie pliku SQL
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}