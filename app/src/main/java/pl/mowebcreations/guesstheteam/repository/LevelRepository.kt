package pl.mowebcreations.guesstheteam.repository

import android.content.Context
import pl.mowebcreations.guesstheteam.data.GuessDatabase
import pl.mowebcreations.guesstheteam.data.Level

class LevelRepository(context: Context) {
    private val levelDao = GuessDatabase.getDatabase(context).LevelDao()

    fun getAllLevels() = levelDao.getAllLevels()

    fun getLevelPlayersById(levelId: Long) = levelDao.getLevelPlayersById(levelId)

    fun getLevelById(id: Long) = levelDao.getLevelById(id)

    fun getEnabledLevelsCount() = levelDao.countEnabledLevels()

    suspend fun showLeagueName(level: Level){
        if(!level.isTeamNameShowed)
        return levelDao.updateLeagueVisibility(level.id)
    }

    suspend fun setLevelCompleted(level: Level){
        levelDao.updateIsLevelCompleted(level.id)
    }
    suspend fun setTeamNameShowed(level: Level){
        levelDao.updateIsTeamNameShowed(level.id)
    }
    suspend fun unBlockNextLevel() = levelDao.setNextLevelEnabled()
    suspend fun setLevelProgressColumnsToFalse() = levelDao.setLevelProgressColumnsToFalse()
    suspend fun setDefaultEnabledLevels()  = levelDao.updateIsLevelEnabledToFalseWhereIdIsGreaterThan5()

}