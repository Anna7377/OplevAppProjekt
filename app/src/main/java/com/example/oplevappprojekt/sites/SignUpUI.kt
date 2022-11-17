package com.example.oplevappprojekt.sites

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.ViewModel.AuthViewModel


// S215722
class SignInUI{

}

@Composable
fun SignInPage(viewModel: AuthViewModel) {

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
            Spacer(modifier = Modifier.height(30.dp))
            InputText("Navn")
            viewModel.onNewMailChange(InputText("Mail"))
            viewModel.onNewPassChange(InputText("Kodeord"))
            viewModel.onConfPassChange(InputText("Gentag Kodeord"))
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
            }
            LogInButton(text = "Opret") {}
            ChangePageText(text="Allerede Oprettet? Log Ind Nu!", onClick = { })

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
    SignInPage(AuthViewModel())
}
