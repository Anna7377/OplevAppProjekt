package com.example.oplevappprojekt.sites

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.ViewModel.Auth
import com.example.oplevappprojekt.ViewModel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.runBlocking
/*

// S215722
class SignInUI{

}

@Composable
fun SignUpPage(viewModel: AuthViewModel, navigation: ()->Unit, navMain: ()-> Unit, state: Auth) {
val state = viewModel.uiState.value
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

            val name = InputText("Navn")
           val mail: String = InputText("Mail")

           val pass= InputText("Kodeord")

          val confpass= InputText("Gentag Kodeord")
            var check: Boolean = false

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
                check=isChecked.value
            }
            val enabled: Boolean
            Spacer(modifier = Modifier.height(10.dp))
            if(check && mail.isNotEmpty() && pass.isNotEmpty()){
                enabled = true
            }
            else {
                enabled = false
            }

            val context = LocalContext.current
            val activity = LocalContext.current as Activity

            LogInButton(text = "Opret", onClick = {
                runBlocking {
                viewModel.SignUp(mail, pass, confpass, context, activity, name)
                    }
                viewModel.emailVerification()
                if (FirebaseAuth.getInstance().currentUser!=null){
                    navMain()
                }
                }, enabled)

            ChangePageText(text="Allerede Oprettet? Log Ind Nu!",onClick = navigation)



        }
    }


}


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
    TextButton( onClick = {dialog.value=true }){
        Text("Jeg accepterer GDPR reglerne",
        color = Color(myColourString.toColorInt()),
        fontSize = 13.sp)
    }
}

@Preview
@Composable
fun SignInPrev(){
    SignUpPage(AuthViewModel(), {}, {}, Auth())
}

 */
