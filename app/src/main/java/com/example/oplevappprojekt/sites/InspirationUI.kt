package com.example.oplevappprojekt.sites

import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
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
        Column(modifier = Modifier
            .height(20.dp)
            .width(20.dp)
            .absoluteOffset(15.dp, 15.dp)) {
            Logo()
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            SmallTitle(text = "Mine Inspirationskilder")
            ScrollableTextField()
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
        fontWeight = FontWeight.ExtraBold,
        modifier=Modifier.padding(top=40.dp, bottom=50.dp)
    )
}


@Composable
fun ScrollableTextField(){
val scrollstate= rememberScrollState()
    Column(modifier= Modifier
        .verticalScroll(scrollstate)
        .border(
            width = 3.dp,
            color = Color(
                myColourString.toColorInt()
            )
        )
        .width(300.dp)
        .height(600.dp)){
        val currentText = remember {
            mutableStateOf(TextFieldValue()) }
        TextField(onValueChange = { currentText.value = it } ,
            modifier = Modifier.width(300.dp),
            placeholder = { Text(text="Indsæt inspirationskilder...") },
            value = currentText.value,
            colors=TextFieldDefaults.
            textFieldColors(backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent)
            )

        } }




@Preview
@Composable
fun InspirationPrev(){
    Inspiration()
}


