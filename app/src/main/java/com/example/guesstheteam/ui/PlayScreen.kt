package com.example.guesstheteam.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guesstheteam.R
import com.example.guesstheteam.data.Level
import com.example.guesstheteam.data.Player
import com.example.guesstheteam.viewModel.LevelViewModel
import kotlinx.coroutines.delay
import java.util.Collections


@Composable
fun PlayScreen(
    totalPoints: Int,
    levels: List<Level>,
    levelViewModel: LevelViewModel,
    onBackClick: () -> Unit,
    onLevelClick: (level: Level) -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        PlayScreenMenu(totalPoints, levels, onBackClick)
        PlayScreenMain(levels, levelViewModel, onLevelClick)
    }
}

@Composable
fun PlayScreenMain(
    levels: List<Level>,
    levelViewModel: LevelViewModel,
    onLevelClick: (level: Level) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.grass),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            alpha = 0.4f
        )
        var itemIndex by remember { mutableIntStateOf(-1) }

        LaunchedEffect(levels) {
            if(levels.isNotEmpty()){
            while (itemIndex < levels.size) {
                delay(300)
                itemIndex++
            }
            }

        }

        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(15.dp),
        ) {
            itemsIndexed(levels) { index, level ->
                AnimatedVisibility(
                    visible = itemIndex >= index,
                    enter = fadeIn(animationSpec = tween(500))
                ) {
                    val players by levelViewModel.getLevelPlayers(level.id)
                        .collectAsState(initial = Collections.emptyList())
                    Button(
                        enabled = level.isEnabled,
                        colors = ButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = colorResource(id = R.color.darkGreen),
                            disabledContentColor = Color.Transparent,
                            disabledContainerColor = colorResource(R.color.darkGreen)
                        ),
                        contentPadding = PaddingValues(0.dp),
                        shape = RectangleShape,
                        modifier = Modifier
                            .alpha(if (level.isEnabled) 1f else 0.5f)
                            .aspectRatio(2 / 3f)
                            .fillMaxWidth()
                            .graphicsLayer(alpha = 0.9f),
                        onClick = { onLevelClick(level) }) {
                        LevelListElement(level, players, 14)

                    }
                }
            }
        }


    }
}

@Composable
fun LevelListElement(level: Level, players: List<Player>, flagSize: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
        ) {
            val maxWidth = maxWidth
            val maxHeight = maxHeight
            Image(
                painter = painterResource(id = R.drawable.grass),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds,
            )
            players.forEach { player ->
                PositionImage(maxWidth, maxHeight, player, false, flagSize)
            }
            if (level.isCompleted) {
                Image(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .rotate(10f),
                    colorFilter = ColorFilter.tint(Color.Green),
                    painter = painterResource(id = R.drawable.baseline_check_box_24),
                    contentDescription = null
                )
            }
            if (!level.isEnabled) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    colorFilter = ColorFilter.tint(Color.White),
                    painter = painterResource(id = R.drawable.baseline_lock_24),
                    contentDescription = null
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White),
                color = colorResource(id = R.color.darkGreen),
                text = if (level.isCompleted) level.shortAnswer else level.id.toString()
            )
        }


    }

}

@Composable
fun PlayScreenMenu(totalPoints: Int, levels: List<Level>, onBackClick: () -> Unit) {
    val levelsCount = levels.size
    val completedLevelsCount = levels.filter { level -> level.isCompleted }.size
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(70.dp)

    ) {
        Button(
            modifier = Modifier
                .size(55.dp),
            contentPadding = PaddingValues(10.dp),
            colors = ButtonColors(
                containerColor = colorResource(id = R.color.darkGreen),
                contentColor = Color.Black,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.DarkGray
            ),
            onClick = { onBackClick() }
        ) {
            Image(
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = null
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .width(200.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_check_box_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorResource(R.color.darkGreen)),
                modifier = Modifier
                    .size(55.dp)
                    .align(Alignment.TopStart)
            )

            Text(
                text = "${completedLevelsCount}/${levelsCount}",
                textAlign = TextAlign.End,
                color = colorResource(id = R.color.darkGreen),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 30.dp, vertical = 10.dp)
            )
        }

        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .width(100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_lightbulb_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorResource(R.color.gold)),
                modifier = Modifier
                    .size(55.dp)
                    .align(Alignment.TopStart)
            )

            Text(
                text = totalPoints.toString(),
                textAlign = TextAlign.End,
                color = colorResource(id = R.color.darkGreen),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .offset(20.dp)
                    .align(Alignment.Center)
                    .padding(horizontal = 30.dp, vertical = 10.dp)
            )
        }
    }
}
