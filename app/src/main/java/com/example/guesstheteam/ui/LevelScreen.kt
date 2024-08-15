package com.example.guesstheteam.ui

import android.media.MediaPlayer
import android.widget.Toast
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guesstheteam.R
import com.example.guesstheteam.data.Level
import com.example.guesstheteam.data.Player
import kotlinx.coroutines.delay

@Composable
fun LevelScreen(
    isAdButtonEnable: Boolean,
    totalPoints: Int,
    lastLevelId: Long,
    level: Level,
    players: List<Player>,
    onAddPlayClick: () -> Unit,
    onShowTeamNameClick: (level: Level) -> Unit,
    onShowPlayerClick: (level: Level) -> Unit,
    onArrowClick: (levelId: Long) -> Unit,
    onCheckClick: (String, Level) -> Unit,
    onBackClick: () -> Unit,
    onLeagueNameClick: (level: Level) -> Unit
) {
    var answer by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LevelScreenMenu(totalPoints, level.id, onBackClick)
        LevelScreenMain(
            isAdButtonEnable,
            lastLevelId,
            level,
            players,
            totalPoints,
            onAddPlayClick,
            onShowTeamNameClick,
            onShowPlayerClick,
            onArrowClick,
            onCheckClick,
            onLeagueNameClick,
            answer,
            onAnswerChange = { newAnswer -> answer = newAnswer }
        )
    }


}


@Composable
fun LevelScreenMain(
    isAdButtonEnable: Boolean,
    lastLevelId: Long,
    level: Level,
    players: List<Player>,
    totalPoints: Int,
    onAddPlayClick: () -> Unit,
    onShowTeamNameClick: (level: Level) -> Unit,
    onShowPlayerClick: (level: Level) -> Unit,
    onArrowClick: (levelId: Long) -> Unit,
    onCheckClick: (String, Level) -> Unit,
    onLeagueNameClick: (level: Level) -> Unit,
    answer: TextFieldValue,
    onAnswerChange: (TextFieldValue) -> Unit
) {
    val context = LocalContext.current
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
                PositionImage(maxWidth, maxHeight, player, true, 55)
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
            val areAllPlayersShowed = players.filter(Player::isShowed).size == 11
            LevelScreenHelpButton(
                enabled = isAdButtonEnable,
                imageId = R.drawable.baseline_play_arrow,
                text = "VIDEO",
                priceText = "+10",
                onClick = { onAddPlayClick()})
            if (!level.isLeagueShowed) {
                LevelScreenHelpButton(
                    imageId = R.drawable.cup,
                    text = "NAZWA LIGI",
                    "20",
                    enabled = !level.isCompleted,
                    onClick = { onLeagueNameClick(level) })
            } else {
                TeamNameShowedButton(level = level)
            }
            LevelScreenHelpButton(
                imageId = R.drawable.shirt,
                text = "POKAŻ GRACZA", "10",
                onClick = { onShowPlayerClick(level) },
                enabled = !(areAllPlayersShowed || level.isCompleted)
            )
            LevelScreenHelpButton(
                imageId = R.drawable.shield,
                text = "NAZWA DRUŻYNY", "90",
                enabled = !(level.isTeamNameShowed || level.isCompleted),
                onClick = {
                    if (totalPoints >= 90) {
                        onShowTeamNameClick(level)
                        onAnswerChange(TextFieldValue(level.answer))
                    } else {
                        Toast.makeText(
                            context,
                            "Nie masz wystarczjącej ilości punktów",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .background(color = Color.Blue)

        ) {
            LevelScreenAnswerBox(
                lastLevelId,
                level,
                onArrowClick,
                onCheckClick,
                answer,
                onAnswerChange
            )
        }
    }
}

@Composable
fun PositionImage(maxWidth: Dp, maxHeight: Dp, player: Player, showNames: Boolean, flagSize: Int) {
    val context = LocalContext.current
    val imageId = remember(player.countryUrl) {

        context.resources.getIdentifier(
            player.countryUrl.lowercase(),
            "drawable",
            context.packageName
        )
    }
    println(player.countryUrl.lowercase())
    Box(
        modifier = Modifier
            .offset(
                ((maxWidth.value * player.position.widthPercent / 100) - flagSize / 2.dp.value).dp,
                ((maxHeight.value * player.position.heightPercent / 100) - flagSize / 2.dp.value).dp
            )
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clip(CircleShape)
                .size(flagSize.dp)
                .background(color = Color.White)

        )
        if (showNames && player.isShowed) {
            Text(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = if (player.name.length >= 10) 5.sp else 8.sp,
                color = colorResource(id = R.color.darkGreen),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .width(flagSize.dp)
                    .background(color = Color.White),
                text = player.name
            )
        }
    }

}

@Composable
fun LevelScreenMenu(totalPoints: Int, levelId: Long, testClick: () -> Unit) {
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

@Composable
fun TeamNameShowedButton(level: Level) {
    val context = LocalContext.current
    val imageId = context.resources.getIdentifier(
        level.league.lowercase(),
        "drawable",
        context.packageName
    )
    Button(
        enabled = false,
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
        onClick = {}
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = imageId),
            contentDescription = null
        )
    }

}

@Composable
fun LevelScreenHelpButton(
    imageId: Int,
    text: String,
    priceText: String,
    onClick: () -> Unit,
    enabled: Boolean = true

) {
    Button(
        enabled = enabled,
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

@Composable
fun LevelScreenAnswerBox(
    lastLevelId: Long,
    level: Level,
    onArrowClick: (levelId: Long) -> Unit,
    onCheckClick: (String, Level) -> Unit,
    answer: TextFieldValue,
    onAnswerChange: (TextFieldValue) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.darkGreen))
    ) {
        Button(
            colors = ButtonColors(
                contentColor = Color.Transparent,
                containerColor = Color.Transparent,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.Gray
            ),
            enabled = level.id > 1,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .size(50.dp),
            onClick = { if (level.id > 1) onArrowClick(level.id - 1) })
        {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                colorFilter = ColorFilter.tint(Color.White),
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = null
            )
        }

        var isAnswerCorrect: Boolean by remember { mutableStateOf(true) }
        var answerTextFieldBgColor by remember { mutableStateOf(Color.White) }


        LaunchedEffect(isAnswerCorrect) {
            if (!isAnswerCorrect) {
                answerTextFieldBgColor = Color(0xFFCF5B5B)
                delay(2000)
                answerTextFieldBgColor = Color.White
                isAnswerCorrect = true
            }
        }



        TextField(
            enabled = !level.isCompleted,
            maxLines = 1,
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.darkGreen)
            ),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clip(RoundedCornerShape(20))
                .background(color = Color.White)
                .width(200.dp)
                .height(50.dp),
            value = if (level.isCompleted || level.isTeamNameShowed) TextFieldValue(level.answer) else answer,
            onValueChange = { textFieldValue -> onAnswerChange(textFieldValue) },
            colors =
            TextFieldDefaults.colors(
                errorContainerColor = Color.White,
                focusedContainerColor = answerTextFieldBgColor,
                disabledContainerColor = colorResource(id = R.color.lightGreen),
                unfocusedContainerColor = answerTextFieldBgColor,
                focusedPlaceholderColor = Color.Gray,
                disabledPlaceholderColor = Color.Gray,
                errorPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray
            ),
            placeholder = {
                Text(
                    color = Color.Gray,
                    text = "Wpisz nazwę drużyny"
                )

            }
        )
        Button(
            enabled = !level.isCompleted,
            shape = RoundedCornerShape(20),
            colors = ButtonColors(
                contentColor = Color.Transparent,
                containerColor = Color.Transparent,
                disabledContentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .size(50.dp),
            onClick = {
                onCheckClick(answer.text, level)
                isAnswerCorrect = level.isCompleted
            }) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                colorFilter = if (level.isCompleted) ColorFilter.tint(colorResource(id = R.color.lightGreen))
                else ColorFilter.tint(Color.White),
                painter = painterResource(id = R.drawable.baseline_check_box_24),
                contentDescription = null
            )
        }
        Button(
            colors = ButtonColors(
                contentColor = Color.Transparent,
                containerColor = Color.Transparent,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.Gray
            ),
            enabled = level.id < lastLevelId,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .size(50.dp),
            onClick = {
                if (level.id < lastLevelId) onArrowClick(level.id + 1)
            }) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                colorFilter = ColorFilter.tint(Color.White),
                painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                contentDescription = null
            )
        }


    }


}
