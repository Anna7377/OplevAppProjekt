package com.example.oplevappprojekt.sites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.R

// S215722
class StartPageUI{

}

@Composable
fun StartPage(){
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .height(400.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(80.dp))
            BigLogo()
            LogoText()
            Text(text="ALLE EVENTYR I LOMMEN", fontSize = 30.sp, color = Color(myColourString.toColorInt()),
                modifier = Modifier.padding(bottom = 60.dp))
            LogInButton(text = "Kom i gang") {

            }
        }
    }



}
@Composable
fun LogoText(){
    Spacer(modifier = Modifier.height(30.dp))
    Image(painter = painterResource(id = R.drawable.logo_text), contentDescription = "",
        modifier = Modifier
            .width(200.dp)
            .height(75.dp))
    Spacer(modifier = Modifier.height(30.dp))
}

@Composable
fun BigLogo(){
    Image(modifier= Modifier
        .height(200.dp)
        .width(200.dp)
        .padding(20.dp, 20.dp, 20.dp, 20.dp)
        ,painter = painterResource(id = R.drawable.logo_photo), contentDescription = "")

}

@Preview
@Composable
fun StartPrev(){
    StartPage()
}