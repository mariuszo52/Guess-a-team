package com.example.guesstheteam

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.guesstheteam.ui.LevelScreen
import com.example.guesstheteam.ui.PlayScreen
import com.example.guesstheteam.ui.SettingsScreen
import com.example.guesstheteam.ui.StartScreen
import com.example.guesstheteam.ui.theme.GuessTheTeamTheme
import com.example.guesstheteam.viewModel.LevelViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.Collections
import kotlin.coroutines.CoroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GuessTheTeamTheme {
                LaunchedEffect(Unit) {
                    val controller = WindowInsetsControllerCompat(window, window.decorView)
                    controller.hide(WindowInsetsCompat.Type.navigationBars())
                    controller.systemBarsBehavior =
                        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
                val levelViewModel = ViewModelProvider(this)[LevelViewModel::class.java]
                val navController = rememberNavController()
                Navigation(
                    navController = navController,
                    levelViewModel = levelViewModel
                )
            }

        }
    }


}

@Composable
fun Navigation(
    levelViewModel: LevelViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            StartScreen(navController)
        }

        composable("settings") {
            SettingsScreen(navController)
        }
        composable("play") {
            val levels by levelViewModel.levelsFlow.collectAsState(initial = Collections.emptyList())
            PlayScreen(
                levels = levels,
                levelViewModel,
                onBackClick = { navController.popBackStack() },
                onLevelClick = { level ->
                    run {
                        val id = level.id
                        navController.navigate("level/$id")
                    }
                })
        }
        composable(
            "level/{levelId}",
            arguments = listOf(navArgument("levelId") { type = NavType.LongType })
        ) { backStackEntry ->
            val levelId = backStackEntry.arguments?.getLong("levelId") ?: 0
            val level by levelViewModel.getLevelById(levelId).collectAsState(initial = null)
            val players by levelViewModel.getLevelPlayers(levelId)
                .collectAsState(initial = Collections.emptyList())
            val coroutineScope = rememberCoroutineScope()
            level?.let {
                LevelScreen(
                    it,
                    players,
                    onAnswerChange = {"lalal".uppercase()},
                    onBackClick = { navController.popBackStack() },
                    onLeagueNameClick = {
                        coroutineScope.launch(Dispatchers.IO) {
                            levelViewModel.showLeagueName(level!!)
                        }
                    }
                )
            }
        }
    }
}


