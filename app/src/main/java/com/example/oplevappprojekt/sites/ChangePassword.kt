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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.ViewModel.Auth
import com.example.oplevappprojekt.ViewModel.AuthViewModel
import org.checkerframework.checker.units.qual.Current

@Preview
@Composable
fun PasswordChange(){
    Password(AuthViewModel(), {})
}

//215726
@Composable
fun Password(viewModel: AuthViewModel, navigation: () -> Unit){
    val state = viewModel.uiState.value
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
                text = "Skift kodeord",
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
                        .height(70.dp))
                    
                    
                    val currentPass = CurrentPassword()

                    Spacer(modifier = Modifier
                        .height(10.dp))

                    val newPass = NewPassword()

                    Spacer(modifier = Modifier
                        .height(10.dp))

                    val confirmNewPass = SaveNewPassword()

                    Spacer(modifier = Modifier
                        .height(10.dp))

                    Button(onClick = {
                                     viewModel.changePassword(currentPass.toString(),newPass.toString(),confirmNewPass.toString())
                        navigation()
                    },
                        shape = RoundedCornerShape(60.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    ) {
                        Text(
                            text = "Gem",
                            color = Color.Black
                        )
                    }
                }

            })
    }
}

@Composable
fun CurrentPassword(){
    var currentPass by remember { mutableStateOf("") }
    TextField(
        value = currentPass,
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
        onValueChange = {newText ->
            currentPass = newText},
        label ={
            Text(text = "Indtast nuværende kodeord:",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold)
        },

        )


}

@Composable
fun NewPassword() {
    var newPass by remember { mutableStateOf("") }
    TextField(
        value = newPass,
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
        onValueChange = {newText ->
            newPass = newText},
        label ={
            Text(text = "Indtast nyt kodeord:",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold)
        },

        )

}

@Composable
fun SaveNewPassword() {
    var confirmNewPass by remember { mutableStateOf("") }
    TextField(
        value = confirmNewPass,
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
        onValueChange = {newText ->
            confirmNewPass = newText},
        label ={
            Text(text = "Gentag nyt kodeord:",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold)
        },

        )

}

