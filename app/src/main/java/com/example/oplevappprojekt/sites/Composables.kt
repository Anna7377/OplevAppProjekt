package com.example.oplevappprojekt.sites

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.CategoryViewModel
import com.example.oplevappprojekt.ViewModel.IdeasViewModel
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel
import java.time.format.TextStyle


// Dynamic Composables, used across multiple sites
//S215722

@Composable
fun TopCard(ImageId: Int, text: String, viewModel: Journeysviewmodel, navMain: () -> Unit) {
    Card(modifier = Modifier.padding(4.dp), elevation = 4.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Box() {
            Image(
                painter = painterResource(id = ImageId),
                contentDescription = " ",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
Row(verticalAlignment = Alignment.Bottom){
    Text(
        text = text,
        modifier = Modifier.padding(top=110.dp),
        textAlign=TextAlign.Left,
        fontSize=40.sp,
        fontWeight = FontWeight.Bold,
        style = androidx.compose.ui.text.TextStyle(shadow = Shadow(color = Color.DarkGray, offset = Offset(7f, 5f), blurRadius = 5f)),
        color = Color.White,

        )
    Spacer(modifier = Modifier.width(150.dp))
    IconButton(modifier = Modifier
        , onClick ={
        val oldPinVal = viewModel.uiState.value.isPinned
        val newPinVal = !oldPinVal
            val oldUnPinVal = viewModel.uiState.value.unPinned
            val newUnPinVal = !oldUnPinVal
        if (viewModel.uiState.value.isJourneySelected) {
            if (viewModel.uiState.value.isPinned){
                viewModel.editJourney(
                    country = viewModel.uiState.value.currentcountry.toString(),
                    date = viewModel.uiState.value.currentdate.toString(),
                    ID = viewModel.uiState.value.currentJourneyID.toString(),
                    isPinned = newPinVal,
                    unPinned = oldPinVal,
                )
            }
            else{
                viewModel.editJourney(
                    country = viewModel.uiState.value.currentcountry.toString(),
                    date = viewModel.uiState.value.currentdate.toString(),
                    ID = viewModel.uiState.value.currentJourneyID.toString(),
                    isPinned = newPinVal,
                    unPinned = oldPinVal
                )
            }

             navMain()
        }}) {
        Image(painter = painterResource(id = R.drawable.pin), contentDescription = "Pin", Modifier.size(80.dp)
        )

}



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
        modifier= Modifier.padding(start = 20.dp)
    )
}

@Composable
fun SmallTitle(text: String) {
    Text(
        text = text, color = Color(myColourString.toColorInt()),
        fontSize = 40.sp,
        fontFamily = FontFamily.SansSerif,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.ExtraBold,
        modifier=Modifier.padding(top=40.dp)
    )
}


@Composable
fun ScrollableTextField()  {
    val scrollstate= rememberScrollState()
    val currentText = remember {
        mutableStateOf(TextFieldValue()) }
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

        TextField(onValueChange = { currentText.value = it } ,
            modifier = Modifier.width(300.dp),
            placeholder = { Text(text="Indsæt inspirationskilder...") },
            value = currentText.value,
            colors= TextFieldDefaults.
            textFieldColors(backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent)
        )

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
fun InputText(hint: String) : String {
    var retText: String = ""
    Column() {
        Text(text=hint, fontSize = 20.sp, textAlign = TextAlign.Left, textDecoration = TextDecoration.Underline)
        val currentText = remember {
            mutableStateOf(TextFieldValue())
        }
        TextField(value = currentText.value,
            onValueChange = { currentText.value = it }, colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White
            ))
        Spacer(modifier = Modifier.height(15.dp))
        retText=currentText.value.text
    }
    return retText
}

@Composable
fun LogInButton(text: String, onClick: () -> Unit, enabled: Boolean) {
    Button(
        onClick = onClick, enabled=enabled, colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(myColourString.toColorInt()),
            contentColor = Color.White,
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
fun ChangePageText(text: String, onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text(text = text,
            color = Color(myColourString.toColorInt()),
        fontWeight = FontWeight.Bold)

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

@Composable
fun Fob(navCreate: ()->Unit){
    FloatingActionButton(
        onClick = {navCreate()},
        backgroundColor = Color(myColourString.toColorInt()),
        contentColor = Color.White,
    ) {
        Icon(Icons.Filled.Add,"")
    }
}

@Composable
fun BottomBar(onClick1: ()-> Unit, onClick2: () -> Unit, onClick3: () -> Unit){
    BottomAppBar(modifier = Modifier
        .height(65.dp)
        .padding(top = 10.dp)) {
        BottomNavigation(backgroundColor = Color(myColourString.toColorInt())) {
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Star,
                        "")
                },
                label = { Text(text = "Inspirationskilder") },
                selected = false,
                onClick = onClick1)

            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Place,
                        "")
                },
                label = { Text(text = "Mine Rejser") },
                selected = false,
                onClick = onClick2)
            BottomNavigationItem(
                icon = {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "" )},
                label = { Text(text = "Profil") },
                selected = false,
                onClick = onClick3 )
        }
    }
}

@Composable
fun Load(navBack: ()->Unit, navIdeas:()->Unit, viewModel: IdeasViewModel){
    Surface(color = Color.Transparent, modifier = Modifier.fillMaxSize()) {
        Text("Indlæser..", color = Color(myColourString.toColorInt())
        , fontSize = 40.sp)
        System.out.println("reloading again")
        if(viewModel.uiState.value.isCategorySelected){
            navBack()
        }
        else{
            navIdeas()
        }

    }
}


