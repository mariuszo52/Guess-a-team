package com.example.guesstheteam

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.example.guesstheteam.ui.TermsScreen
import com.example.guesstheteam.ui.theme.GuessTheTeamTheme
import com.example.guesstheteam.viewModel.LevelViewModel
import com.example.guesstheteam.viewModel.PlayerViewModel
import com.example.guesstheteam.viewModel.PointsViewModel
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Collections

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            MobileAds.initialize(applicationContext)
        }
        var rewardedAd: RewardedAd? by mutableStateOf(null)
        var isAdButtonEnable: Boolean by mutableStateOf(false)
        loadAd(context = applicationContext,
            onAdLoaded = { value -> rewardedAd = value },
            setAdButtonEnable = { value -> isAdButtonEnable = value }
        )
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
                val pointsViewModel = ViewModelProvider(this)[PointsViewModel::class]
                val navController = rememberNavController()
                Navigation(
                    isAdButtonEnable = isAdButtonEnable,
                    pointsViewModel = pointsViewModel,
                    rewardedAd = rewardedAd,
                    playerViewModel = playerViewModel,
                    navController = navController,
                    levelViewModel = levelViewModel,
                    activity = this,
                    setAdButtonEnable = { newValue -> isAdButtonEnable = newValue },
                    setRewardedAd = { newValue -> rewardedAd = newValue }
                )
            }

        }
    }


}

fun loadAd(
    context: Context,
    setAdButtonEnable: (Boolean) -> Unit,
    onAdLoaded: (RewardedAd?) -> Unit
) {
    val tag = "AD"
    val adRequest = AdRequest.Builder().build()
    RewardedAd.load(
        context,
        "ca-app-pub-8472043461397762/5450032256",
        adRequest,
        object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(p0: LoadAdError) {
                Log.d(tag, p0.toString())
                onAdLoaded(null)
                setAdButtonEnable(false)
            }

            override fun onAdLoaded(p0: RewardedAd) {
                Log.d(tag, "Ad loaded")
                onAdLoaded(p0)
                setAdButtonEnable(true)
            }
        })
}


fun showAd(
    pointsViewModel: PointsViewModel,
    rewardedAd: RewardedAd?,
    activity: Activity,
    setAdButtonEnable: (Boolean) -> Unit,
    setRewardedAd: (RewardedAd?) -> Unit
) {
    val tag = "AD"
    rewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
        override fun onAdDismissedFullScreenContent() {
            Log.d(tag, "Ad dismissed fullscreen content.")
            setRewardedAd(null)
            setAdButtonEnable(false)
            loadAd(
                context = activity.applicationContext,
                setAdButtonEnable = { setAdButtonEnable(true) },
                onAdLoaded = { newValue -> setRewardedAd(newValue) }
            )

        }

        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            Log.d(tag, "Failed to show fullscreen content. $p0")
            setRewardedAd(null)
            setAdButtonEnable(false)
            loadAd(
                context = activity.applicationContext,
                setAdButtonEnable = { setAdButtonEnable(true) },
                onAdLoaded = { newValue -> setRewardedAd(newValue) }
            )
        }
    }

    rewardedAd?.show(activity) { rewardItem ->
        CoroutineScope(Dispatchers.IO).launch {
            pointsViewModel.updatePoints(rewardItem.amount)
            Log.d(tag, "User earned the reward ${rewardItem.type} ${rewardItem.amount}")
            setAdButtonEnable(false)
        }
    }
}


@Composable
fun Navigation(
    isAdButtonEnable: Boolean,
    activity: Activity,
    rewardedAd: RewardedAd?,
    pointsViewModel: PointsViewModel,
    playerViewModel: PlayerViewModel,
    levelViewModel: LevelViewModel,
    navController: NavHostController,
    setRewardedAd: (rewardedAd: RewardedAd?) -> Unit,
    setAdButtonEnable: (Boolean) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val totalPoints by levelViewModel.totalPoints.collectAsState(initial = 0)

    NavHost(navController = navController, startDestination = "start") {
        composable("terms") {
            TermsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("start") {
            StartScreen(
                onSettingsClick = { navController.navigate("settings") },
                onPremiumClick = {
                    Toast.makeText(
                        activity.applicationContext,
                        "Wkrótce dostępne",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onPlayClick = { navController.navigate("play") }
            )
        }

        composable("settings") {
            SettingsScreen(
                onBackClick = { navController.popBackStack() },
                onTermsClick = { navController.navigate("terms") },
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
            route = "level/{levelId}",
            arguments = listOf(navArgument("levelId") { type = NavType.LongType })
        ) { backStackEntry ->
            val levelId = backStackEntry.arguments?.getLong("levelId") ?: 0
            val level by levelViewModel.getLevelById(levelId).collectAsState(initial = null)
            val players by levelViewModel.getLevelPlayers(levelId)
                .collectAsState(initial = Collections.emptyList())
            val levelsCount by levelViewModel.getAllLevelsCount().collectAsState(initial = 0)
            level?.let {
                LevelScreen(
                    isAdButtonEnable,
                    totalPoints,
                    levelsCount,
                    it,
                    players,
                    onAddPlayClick = {
                        showAd(
                            pointsViewModel,
                            rewardedAd,
                            activity,
                            setRewardedAd = { newAd -> setRewardedAd(newAd) },
                            setAdButtonEnable = { newValue -> setAdButtonEnable(newValue) }
                        )
                    },
                    onShowTeamNameClick = {
                        coroutineScope.launch(Dispatchers.IO) {
                            levelViewModel.setTeamNameShowed(
                                level!!, totalPoints
                            )
                        }
                    },
                    onShowPlayerClick = {
                        coroutineScope.launch(Dispatchers.IO) {
                            playerViewModel.showPlayer(
                                level!!,
                                totalPoints
                            )
                        }
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


