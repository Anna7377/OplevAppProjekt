import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oplevappprojekt.R
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.ViewModel.CollaboratorViewmodel
import com.example.oplevappprojekt.sites.myColor
import com.example.oplevappprojekt.sites.myColourString

@Composable
fun invite(viewmodel: CollaboratorViewmodel, navBack: () -> Unit){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(Color(myColourString.toColorInt()))
                .height(350.dp)
                .width(350.dp),
            contentAlignment = Alignment.Center
            ){

            Text(
                text = "Tilføj via link",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                val link = regcolab()
                var enabled = false
                if(link.isNotEmpty()){
                    enabled = true
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = { viewmodel.addJourney(link) }, enabled = enabled) {
                    Text("Tilføj")
                }
                Text(text=viewmodel.uiState.value.retText)
            }
        }
    }
    Box(modifier = Modifier
        .size(30.dp)
        .absoluteOffset(x = 335.dp, y = 230.dp)){
        Image(painter = painterResource(id = R.drawable.close), contentDescription = "", modifier = Modifier.fillMaxSize().clickable(onClick = {navBack()}))
    }}




@Composable
fun regcolab() : String{
    val text = rememberSaveable {
        mutableStateOf("")
    }
    TextField(value = text.value, onValueChange = {text.value = it},
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White) )

    return text.value
}
