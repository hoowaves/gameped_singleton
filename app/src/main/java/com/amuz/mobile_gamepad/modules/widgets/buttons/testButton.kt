package com.amuz.mobile_gamepad.modules.widgets.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun testButton() {
    var buttonPressed by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
//            .neu(
//                lightShadowColor = Color.Gray.copy(alpha = 0.3f),
//                darkShadowColor = Color.Black.copy(alpha = 0.3f) ,
//                shadowElevation = 10.dp,
//                lightSource = LightSource.LEFT_TOP,
//                shape = if (buttonPressed) Pressed(RoundedCorner(24.dp)) else Flat(RoundedCorner(24.dp)),
//            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                buttonPressed = !buttonPressed
            },
        shape = RoundedCornerShape(24.dp),
    ){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
        )
    }

}