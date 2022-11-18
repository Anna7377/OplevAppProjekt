package com.example.oplevappprojekt.model

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

// s215718 & s213370

@Composable
    fun LogInPage(navController : NavHostController){
        Column(modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        val brugernavn = remember{ mutableStateOf(TextFieldValue())}
        val password = remember {mutableStateOf(TextFieldValue())}}

        Text(text = "LogIn", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(label = { Text(text = "Bruger Navn")},value = brugernavn.value,onValueChange = {brugernavn.value = it})

        Spacer(modifier = Modifier.height(20.dp))
        TextField(label = {Text(text = "Password")},value = password.value,visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = {password.value = it})

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp,0.dp,40.dp,0.dp)){
            Button(onClick = { },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)){
                Text(text = "LogIn")
                 }

            }
        }




