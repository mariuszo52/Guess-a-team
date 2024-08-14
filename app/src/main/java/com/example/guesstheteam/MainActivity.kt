package com.example.guesstheteam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.guesstheteam.viewModel.PlayerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Collections

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
                val playerViewModel = ViewModelProvider(this)[PlayerViewModel::class.java]
                val navController = rememberNavController()
                Navigation(
                    playerViewModel = playerViewModel,
                    navController = navController,
                    levelViewModel = levelViewModel
                )
            }

        }
    }


}

@Composable
fun Navigation(
    playerViewModel: PlayerViewModel,
    levelViewModel: LevelViewModel,
    navController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()
    val totalPoints by levelViewModel.totalPoints.collectAsState(initial = 0)

    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            StartScreen(navController)
        }

        composable("settings") {
            SettingsScreen(
                onBackClick = { navController.popBackStack() },
                onTermsClick = {},
                onResetClick = { coroutineScope.launch(Dispatchers.IO) { levelViewModel.resetProgress() } }
            )
        }
        composable("play") {
            val levels by levelViewModel.levelsFlow.collectAsState(initial = Collections.emptyList())
            PlayScreen(
                totalPoints = totalPoints,
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
            val levelsCount by levelViewModel.getAllLevelsCount().collectAsState(initial = 0)
            level?.let {
                LevelScreen(
                    totalPoints,
                    levelsCount,
                    it,
                    players,
                    onShowTeamNameClick = {
                        coroutineScope.launch(Dispatchers.IO) {
                            levelViewModel.setTeamNameShowed(
                                level!!, totalPoints
                            )
                        }
                    },
                    onShowPlayerClick = {
                        coroutineScope.launch(Dispatchers.IO) { playerViewModel.showPlayer(level!!, totalPoints) }
                    },
                    onCheckClick = { answer, level ->
                        levelViewModel.onCheckClick(level, answer)
                    },
                    onBackClick = { navController.popBackStack() },
                    onArrowClick = { levelId -> navController.navigate("level/$levelId") },
                    onLeagueNameClick = {
                        coroutineScope.launch(Dispatchers.IO) {
                            levelViewModel.showLeagueName(level!!, totalPoints)
                        }
                    }
                )
            }
        }
    }
}


