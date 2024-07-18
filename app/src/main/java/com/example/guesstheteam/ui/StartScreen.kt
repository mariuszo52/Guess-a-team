package com.example.guesstheteam.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guesstheteam.R
import com.example.guesstheteam.ui.theme.GuessTheTeamTheme

@Composable
fun StartScreen(onPlayClick: () -> Unit) {
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
                fontSize = 60.sp,
                fontFamily = FontFamily(Font(R.font.bungge_outline)),
                style = MaterialTheme.typography.bodyLarge.copy(
                    lineHeight = 70.sp,
                    textAlign = TextAlign.Center
                ),
                text = "ODGADNIJ DRUŻYNĘ"
            )
            Text(
                modifier = Modifier
                    .padding(top = 50.dp, bottom = 50.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.DarkGray)
                    .padding(30.dp),
                text = "SEZON 2023/24"
            )
            Button(
                modifier = Modifier.width(100.dp),
                onClick = { onPlayClick() }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_play),
                    contentDescription = null
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier.width(100.dp),
                    onClick = { onPlayClick() }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_settings),
                        contentDescription = null
                    )
                }
                Button(
                    modifier = Modifier.width(100.dp),
                    onClick = { onPlayClick() }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_premium),
                        contentDescription = null
                    )
                }

            }
        }
    }


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StartScreenPreview() {
    GuessTheTeamTheme {
        StartScreen(onPlayClick = {})

    }
}