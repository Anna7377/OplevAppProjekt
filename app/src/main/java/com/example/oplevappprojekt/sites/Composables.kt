package com.example.oplevappprojekt.sites

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.R
import java.time.format.TextStyle

@Composable
fun TopCard(ImageId: Int, text: String) {
    Card(modifier = Modifier.padding(4.dp), elevation = 4.dp,
        shape = RoundedCornerShape(25.dp)
    ) {
        Box() {
            Image(
                painter = painterResource(id = ImageId),
                contentDescription = " ",
                modifier = Modifier
                    .fillMaxWidth().height(200.dp),
                contentScale = ContentScale.Crop
            )
Row(){
    Text(
        text = text,
        modifier = Modifier.padding(top=120.dp),
        textAlign=TextAlign.Left,
        fontSize=60.sp,
        style = androidx.compose.ui.text.TextStyle(shadow = Shadow(color = Color.Black, offset = Offset(7f, 5f), blurRadius = 5f)),
        color = Color.White,

        )
    Text(text="+",
        modifier = Modifier.clickable(onClick = {/*TODO*/}).padding(top=120.dp, start=100.dp),
        textAlign=TextAlign.Right,
        fontSize=60.sp,
        fontWeight = FontWeight.Light,
        style = androidx.compose.ui.text.TextStyle(shadow = Shadow(color = Color.Black, offset = Offset(2f, 2f), blurRadius = 5f)),
        color = Color.White)
}


        }
    }
}

@Composable
fun SingleJourneyTitle(text: String){
    Text(
        text = text, color = Color(myColourString.toColorInt()),
        fontSize = 47.sp,
        fontFamily = FontFamily.SansSerif,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.ExtraBold,
        modifier= Modifier.padding(start = 27.dp)
    )
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
            colors= TextFieldDefaults.
            textFieldColors(backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent)
        )

    } }

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
    Column() {
        Text(text=hint, fontSize = 20.sp, textAlign = TextAlign.Left, textDecoration = TextDecoration.Underline)
        val currentText = remember {
            mutableStateOf(TextFieldValue())
        }
        TextField(value = currentText.value,
            onValueChange = { currentText.value = it })
        Spacer(modifier = Modifier.height(40.dp))
    }

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
            contentDescription = "", contentScale = ContentScale.FillHeight,
            modifier = Modifier.clickable(enabled=true, onClickLabel = null, onClick = {/*TODO*/})
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

