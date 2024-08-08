package com.example.guesstheteam.ui

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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.NavController
import com.example.guesstheteam.R
import com.example.guesstheteam.data.Level
import com.example.guesstheteam.data.Player
import com.example.guesstheteam.viewModel.LevelViewModel
import java.util.Collections


@Composable
fun PlayScreen(
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
        PlayScreenMenu(onBackClick)
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
        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(15.dp),


            ) {
            items(levels) { level ->
                val players by levelViewModel.getLevelPlayers(level.id)
                    .collectAsState(initial = Collections.emptyList())
                Button(
                    colors = ButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = colorResource(id = R.color.darkGreen),
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = colorResource(R.color.darkGreen)
                    ),
                    contentPadding = PaddingValues(0.dp),
                    shape = RectangleShape,
                    modifier = Modifier
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

@Composable
fun LevelListElement(level: Level, players: List<Player>, flagSize: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
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
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White),
                color = colorResource(id = R.color.darkGreen),
                text = level.id.toString()
            )
        }


    }

}

@Composable
fun PlayScreenMenu(onBackClick: () -> Unit) {
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
                text = "116/120",
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
                text = "200",
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
