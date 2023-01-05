package com.example.oplevappprojekt.sites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.ViewModel.CategoryViewModel

//s215726
@Composable
fun CreateIdea(navIdeas: ()->Unit) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(Color(myColourString.toColorInt()))
                .height(350.dp)
                .width(350.dp),

            ) {
            Text(
                text = "Opret Ide",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
            )
        }

        MaterialTheme(
            content = {
                Column(
                    //modifier = Modifier.height(200.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {

                    Spacer(modifier = Modifier
                        .height(60.dp))

                    Spacer(modifier = Modifier
                        .height(10.dp))



                    Spacer(modifier = Modifier
                        .height(10.dp))

                    Button(onClick = {
                        navIdeas()
                     },
                        shape = RoundedCornerShape(60.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    ) {
                        Text(
                            text = "Opret",
                            color = Color.Black
                        )
                    }
                }

            })
    }}

@Composable
fun Title() : String {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Transparent),
        modifier = Modifier
            .height(49.dp)
            .width(250.dp)
            .offset(x = 2.dp),
        shape = RoundedCornerShape(8.dp),
        onValueChange = {newText -> {

        }
             },
        label ={
            Text(text = "Titel:",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold)
        },

        )
return text
}

@Composable
fun Descriptions() : String {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Transparent),
        modifier = Modifier
            .height(120.dp)
            .width(250.dp)
            .offset(x = 2.dp),
        shape = RoundedCornerShape(8.dp),
        onValueChange = {newText ->
            },
        label ={
            Text(text = "Beskrivelse:",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold)
        },

        )
return text
}
