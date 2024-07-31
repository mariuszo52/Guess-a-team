package com.example.guesstheteam.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.guesstheteam.R


@Composable
fun SettingsScreen(onBackClick: () -> Unit) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .height(70.dp)

            ) {
            Button(
                modifier = Modifier
                    .width(60.dp),
                contentPadding = PaddingValues(10.dp),
                colors = ButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.DarkGray
                ),

                onClick = onBackClick
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = null
                )
            }
            Text(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.ustawienia)
            )
        }
        HorizontalDivider(
            color = Color.LightGray,
            thickness = 1.dp
        )
        Column(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .border(
                    color = Color.LightGray,
                    width = 1.dp,
                    shape = RoundedCornerShape(10.dp)
                )
                .fillMaxWidth(0.9f)
                .height(intrinsicSize = IntrinsicSize.Max),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height(70.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MenuButton(
                    image = painterResource(id = R.drawable.baseline_delete_24),
                    text = stringResource(id = R.string.reset),
                ) { onBackClick() }
            }
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height(70.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MenuButton(
                    image = painterResource(id = R.drawable.baseline_shield_24),
                    text = stringResource(id = R.string.terms)
                )
                { onBackClick() }
            }

        }
        Column {

        }
    }
}

@Composable
fun MenuButton(image: Painter, text: String, onClick: () -> Unit) {
    Button(
        contentPadding = PaddingValues(0.dp),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxSize(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,

            ) {
            Image(
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = colorResource(id = R.color.darkGreen))
                    .padding(5.dp)
                    .fillMaxHeight(),
                painter = image,
                contentDescription = null
            )

            Text(
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 10.dp),
                fontWeight = FontWeight.Bold,
                text = text
            )
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(onBackClick = {})
}