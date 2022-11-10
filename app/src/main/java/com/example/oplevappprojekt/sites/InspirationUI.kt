package com.example.oplevappprojekt.sites

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt

class InspirationUI{

}
@Composable
fun Inspiration(){
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(){
            SmallTitle(text = "Mine Inspirationskilder")
        }

        }
}
@Composable
fun SmallTitle(text: String) {
    Text(
        text = text, color = Color(myColourString.toColorInt()),
        fontSize = 47.sp,
        fontFamily = FontFamily.SansSerif,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.ExtraBold
    )
}

@Preview
@Composable
fun InspirationPrev(){
    Inspiration()
}
