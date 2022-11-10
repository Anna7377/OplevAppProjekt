package com.example.oplevappprojekt.sites


import android.text.style.UnderlineSpan
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.R

class LogInUI {
}

@Composable
fun LoginPage() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(modifier = Modifier
            .height(20.dp)
            .width(20.dp).absoluteOffset(15.dp, 15.dp)) {
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
            ChangePageText("Ny til Oplev? Opret Bruger Nu!")

        }
    }

}

val myColourString = "#455467"

@Composable
fun Title(text: String) {
    Text(
        text = text, color = Color(myColourString.toColorInt()),
        fontSize = 60.sp,
        fontFamily = FontFamily.SansSerif,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.ExtraBold
    )
}

@Composable
fun InputText(hint: String) {
    Text(text=hint, fontSize = 20.sp, textAlign = TextAlign.Start)
    val currentText = remember {
        mutableStateOf(TextFieldValue())
    }
    TextField(value = currentText.value,
        onValueChange = { currentText.value = it })
    Spacer(modifier = Modifier.height(40.dp))
}

@Composable
fun LogInButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(myColourString.toColorInt()),
            contentColor = Color.White
        )
    ) {
        Text(text = text)

    }
}

@Composable
fun Logo() {
    Surface(modifier = Modifier.height(60.dp)) {
        Image(
            painter = painterResource(id = R.drawable.logo_photo),
            contentDescription = "", contentScale = ContentScale.FillHeight
        )
    }
}

@Composable
fun ChangePageText(text: String) {
    TextButton(onClick = { /*TODO*/ }) {
        Text(text = text,
            color = Color(myColourString.toColorInt()))

    }
}

@Preview
@Composable
fun LoginPrev() {
    LoginPage()
}

