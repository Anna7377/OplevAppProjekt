package com.example.oplevappprojekt.sites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.ViewModel.AuthViewModel
import com.example.oplevappprojekt.ViewModel.CollaboratorViewmodel
import com.example.oplevappprojekt.myColor

class InviteCol {
}

@Composable
fun invite(viewmodel: CollaboratorViewmodel){
    Box( modifier = Modifier
        .background(Color(myColor.toColorInt()))
        .height(350.dp)
        .width(350.dp)){
        Box(contentAlignment = Alignment.BottomCenter) {

            Text(
                text = "Join via Link",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val link = regcolab()
                Button(onClick = { viewmodel.addJourney(link) }) {
Text("Join")
                }
                Text(text=viewmodel.uiState.value.retText)
            }
        }
    }
}

@Composable
fun regcolab() : String{
    val text = rememberSaveable {
        mutableStateOf("")
    }
    TextField(value = text.value, onValueChange = {text.value = it},
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White) )
    
    return text.value
}

