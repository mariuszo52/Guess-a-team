package com.example.guesstheteam

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.guesstheteam.ui.LevelScreen
import com.example.guesstheteam.ui.PlayScreen
import com.example.guesstheteam.ui.SettingsScreen
import com.example.guesstheteam.ui.StartScreen
import com.example.guesstheteam.ui.theme.GuessTheTeamTheme
import com.example.guesstheteam.viewModel.LevelViewModel
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
                val vm = ViewModelProvider(this)[LevelViewModel::class.java]
                val navController = rememberNavController()
                Navigation(navController = navController, vm = vm)

            }

        }
    }


}

@Composable
fun Navigation(vm: LevelViewModel, navController: NavHostController) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            StartScreen(navController)
        }

        composable("settings") {
            SettingsScreen(navController)
        }
        composable("play") {
            val levels by vm.levels.collectAsState(initial = Collections.emptyList())
            PlayScreen(
                levels = levels,
                navController)
        }
        composable("level") {
            LevelScreen()
        }
    }

}

