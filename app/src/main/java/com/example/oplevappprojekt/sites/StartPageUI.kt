package com.example.oplevappprojekt.sites

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt

// S215722
class StartPageUI{

}

@Composable
fun StartPage(navigate: ()-> Unit){
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
            LogInButton(text = "Kom i gang", onClick=navigate, enabled = true)
        }
    }



}

@Preview
@Composable
fun StartPrev(){
StartPage (navigate={})
}
