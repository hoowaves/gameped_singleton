package com.amuz.mobile_gamepad.module.widgets.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.amuz.mobile_gamepad.R

@Composable
fun LoadingPage(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF000000), Color(0xFF363636)),
                    start = Offset(0f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                ),
                shape = RectangleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "MOBILE\nGAMEPAD",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = Color.White,
                    letterSpacing = 0.03.em,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.wrapContentSize()
            )
            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(114.dp)
                    .height(70.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.splash),
                    contentDescription = "splash"
                )
            }
        }
    }
}