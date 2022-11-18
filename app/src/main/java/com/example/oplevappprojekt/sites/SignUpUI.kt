package com.example.oplevappprojekt.sites

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.ViewModel.Auth
import com.example.oplevappprojekt.ViewModel.AuthViewModel


// S215722
class SignInUI{

}

@Composable
fun SignUpPage(viewModel: AuthViewModel, navigation: ()->Unit, navMain: ()-> Unit) {
    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(modifier = Modifier
            .height(20.dp)
            .width(20.dp)
            .absoluteOffset(15.dp, 15.dp)) {
            Logo()
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(70.dp))
            Title("Opret Bruger")
            Spacer(modifier = Modifier.height(10.dp))
            InputText("Navn")
           val typedmail: String = InputText("Mail")
            viewModel.onNewMailChange(typedmail)
           val pass= InputText("Kodeord")
            viewModel.onNewPassChange(pass)
          val confpass= InputText("Gentag Kodeord")
            viewModel.onConfPassChange(confpass)
            Row(modifier = Modifier.height(30.dp)){
                GDPR()
                Spacer(modifier = Modifier.width(20.dp))
                val isChecked = remember { mutableStateOf(false) }
                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = { isChecked.value = it },
                    enabled = true,
                    colors = CheckboxDefaults.colors(checkmarkColor = Color(myColourString.toColorInt())),
                    modifier = Modifier
                        .size(3.dp)
                        .padding(0.dp, 13.dp),
                )
                viewModel.IsChecked(isChecked.value)
            }
            Spacer(modifier = Modifier.height(5.dp))
            LogInButton(text = "Opret", onClick = {viewModel.createUser(context)})
            ChangePageText(text="Allerede Oprettet? Log Ind Nu!", onClick = navigation)

        }
    }


}

@Preview
@Composable
fun GDPR(){
    val dialog = remember{mutableStateOf(true)}

    if(dialog.value){
        AlertDialog(onDismissRequest = {dialog.value=false},
            title = { Text(text="GDPR Regler", color = Color.White) },
            text={ Text(text="...", color = Color.White) },
            confirmButton = { TextButton(onClick = {dialog.value=false}) { Text(text="Ok", color = Color.White) } },
            backgroundColor = Color(myColourString.toColorInt()))
    }
    ChangePageText(text = "Jeg accepterer GDPR regler mm.", onClick = {dialog.value=true } )
}

@Preview
@Composable
fun SignInPrev(){
    SignUpPage(AuthViewModel(), {}, {})
}
