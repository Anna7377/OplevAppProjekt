package com.example.oplevappprojekt.sites



import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// S215722

class LogInUI {
}

@Composable
fun LoginPage(navigation: ()-> Unit) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(modifier = Modifier
            .height(20.dp)
            .width(20.dp)
            .absoluteOffset(15.dp, 15.dp)) {
            Logo()
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(90.dp))
            Title("Log Ind")
            Spacer(modifier = Modifier.height(40.dp))
            InputText("Mail")
            InputText("Password")
            Spacer(modifier = Modifier.height(60.dp))
            LogInButton(text = "Log Ind") {}
            Spacer(modifier = Modifier.height(40.dp))
            ChangePageText("Ny til Oplev? Opret Bruger Nu!", navigation)

        }
    }

}



@Preview
@Composable
fun LoginPrev() {
    LoginPage(navigation = {})
}

