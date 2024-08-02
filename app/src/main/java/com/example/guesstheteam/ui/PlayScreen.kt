package com.example.guesstheteam.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guesstheteam.R

@Composable
fun PlayScreen(onBackClick: () -> Unit) {
    PlayScreenMenu(onBackClick)
}

@Composable
fun PlayScreenMenu(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ){



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
                onClick = onBackClick
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
}

@Preview
@Composable
fun PlayScreenPreview() {
    PlayScreen({})
}