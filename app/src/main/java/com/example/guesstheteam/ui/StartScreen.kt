package com.example.guesstheteam.ui


import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guesstheteam.R
import kotlinx.coroutines.delay

@Composable
fun StartScreen(
    onPlayClick: () -> Unit,
    onPremiumClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Image(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        painter = painterResource(id = R.drawable.grass_background),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alpha = 0.8f
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .padding(top = 150.dp)
                .height(200.dp),
            fontSize = 60.sp,
            fontFamily = FontFamily(Font(R.font.bungge_outline)),
            style = MaterialTheme.typography.bodyLarge.copy(
                lineHeight = 70.sp,
                textAlign = TextAlign.Center
            ),
            text = stringResource(id = R.string.odgadnij_druzyne)
        )
        Text(
            modifier = Modifier
                .padding(top = 50.dp, bottom = 50.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = colorResource(id = R.color.darkGreen))
                .padding(30.dp),
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.sezon)
        )
        var playButtonSize by remember { mutableStateOf(100.dp) }
        val animatedPlayButtonSize by animateDpAsState(
            targetValue = playButtonSize,
            label = "playButtonAnimate"
        )

        LaunchedEffect(Unit) {
            while (true) {
                playButtonSize = 100.dp
                delay(250)
                playButtonSize = 105.dp
                delay(250)

            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
        ) {
            Button(
                modifier = Modifier
                    .size(animatedPlayButtonSize)
                    .border(width = 5.dp, shape = CircleShape, color = Color.White),
                colors = ButtonColors(
                    containerColor = colorResource(id = R.color.darkGreen),
                    contentColor = Color.White,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.DarkGray
                ),
                onClick = { onPlayClick() }
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.baseline_play_arrow_24),
                    contentDescription = null,

                    )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(100.dp, Alignment.CenterHorizontally),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(100.dp)
            ) {
                Button(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 5.dp, shape = CircleShape, color = Color.White),
                    colors = ButtonColors(
                        containerColor = colorResource(id = R.color.darkGreen),
                        contentColor = Color.White,
                        disabledContentColor = Color.Gray,
                        disabledContainerColor = Color.DarkGray
                    ),
                    onClick = { onSettingsClick() }
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.ic_settings),
                        contentDescription = null,

                        )
                }
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.ustawienia),
                    textAlign = TextAlign.Center
                )
            }

            Column {
                Button(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(
                            width = 5.dp,
                            shape = CircleShape,
                            color = colorResource(id = R.color.gold)
                        ),
                    colors = ButtonColors(
                        containerColor = Color.White,
                        contentColor = colorResource(id = R.color.gold),
                        disabledContentColor = Color.Gray,
                        disabledContainerColor = Color.DarkGray
                    ),
                    onClick = { onPremiumClick() }
                ) {
                    Image(
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.gold)),
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.baseline_workspace_premium_24),
                        contentDescription = null,

                        )
                }
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.premium),
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

