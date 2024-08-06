package com.example.guesstheteam.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guesstheteam.R
import com.example.guesstheteam.data.Level
import com.example.guesstheteam.data.Player
import com.example.guesstheteam.data.Position

@Composable
fun LevelScreen(level: Level, players: List<Player>, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LevelScreenMenu(levelId = level.id, onBackClick)
        LevelScreenMain(level, players)
    }


}


@Composable
fun LevelScreenMain(level: Level, players: List<Player>) {
    Column(
        modifier = Modifier
            .background(color = Color.Red)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
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
                PositionImage(maxWidth, maxHeight, player.position)
            }


        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .background(color = Color.White)


        ) {
            LevelScreenHelpButton(
                imageId = R.drawable.cup,
                text = "NAZWA LIGI",
                "20",
                onClick = { println("click LIGA") })
            LevelScreenHelpButton(
                imageId = R.drawable.shirt,
                text = "POKAŻ GRACZA", "10",
                onClick = { println("click LIGA") })
            LevelScreenHelpButton(
                imageId = R.drawable.shield,
                text = "NAZWA DRUŻYNY", "90",
                onClick = { println("click LIGA") })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .background(color = Color.Blue)

        ) {
            Text(text = "TODO / INSERT RESPONSE FIELD")
        }
    }
}

@Composable
fun PositionImage(maxWidth: Dp, maxHeight: Dp, position: Position) {
    Image(
        painter = painterResource(id = R.drawable.testflag),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .offset(
                ((maxWidth.value * position.widthPercent / 100) - 30.dp.value).dp,
                ((maxHeight.value * position.heightPercent / 100) - 30.dp.value).dp
            )
            .clip(CircleShape)
            .size(60.dp)
            .background(color = Color.White)

    )

}

@Composable
fun LevelScreenMenu(levelId: Long, testClick: () -> Unit) {
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
            onClick = testClick
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
            Text(
                text = "Poziom $levelId",
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

@Composable
fun LevelScreenHelpButton(imageId: Int, text: String, priceText: String, onClick: () -> Unit) {
    Button(
        colors = ButtonColors(
            containerColor = colorResource(id = R.color.darkGreen),
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        ),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .size(60.dp),
        shape = RoundedCornerShape(15),
        onClick = onClick
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Text(
                color = colorResource(id = R.color.darkGreen),
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .background(color = colorResource(id = R.color.gold)),
                text = priceText
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    modifier = Modifier
                        .padding(bottom = 2.dp)
                        .size(15.dp),
                    painter = painterResource(id = imageId),
                    contentDescription = null
                )
                Text(
                    lineHeight = 10.sp,
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    text = text
                )
            }
        }

    }

}
