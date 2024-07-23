package com.example.guesstheteam.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.guesstheteam.R


@Composable
fun SettingsScreen(onBackClick: () -> Unit) {


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .height(70.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                contentPadding = PaddingValues(10.dp),
                modifier = Modifier
                    .size(50.dp),
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

                fontWeight = FontWeight.Bold,
                text = "Ustawienia"
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
                Button(
                    contentPadding = PaddingValues(5.dp),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .size(50.dp),
                    colors = ButtonColors(
                        containerColor = colorResource(id = R.color.darkGreen),
                        contentColor = Color.White,
                        disabledContentColor = Color.Gray,
                        disabledContainerColor = Color.DarkGray
                    ),

                    onClick = onBackClick
                ) {
                    Image(
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.baseline_delete_24),
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    fontWeight = FontWeight.Bold,
                    text = "Resetuj postęp"
                )
            }

            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height(70.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    contentPadding = PaddingValues(5.dp),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .size(50.dp),
                    colors = ButtonColors(
                        containerColor = colorResource(id = R.color.darkGreen),
                        contentColor = Color.White,
                        disabledContentColor = Color.Gray,
                        disabledContainerColor = Color.DarkGray
                    ),

                    onClick = onBackClick
                ) {
                    Image(
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.baseline_shield_24),
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    fontWeight = FontWeight.Bold,
                    text = "Warunki Korzystania"
                )
            }


        }
        Column {

        }
    }
}


@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(onBackClick = {})
}