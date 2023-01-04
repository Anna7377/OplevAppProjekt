package com.example.oplevappprojekt.sites



import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oplevappprojekt.ViewModel.Auth
import com.example.oplevappprojekt.ViewModel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking

// S215722

class LogInUI {
}

@Composable
fun LoginPage(navigation: ()-> Unit, viewModel: AuthViewModel, navMain: ()-> Unit) {
    val state = viewModel.uiState.value

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .height(20.dp)
                .width(20.dp)
                .absoluteOffset(15.dp, 15.dp)
        ) {
            Logo()
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(90.dp))
            Title("Log Ind")
            Spacer(modifier = Modifier.height(40.dp))
            val mail = InputText("Mail")
            val pass = InputText("Password")
            Spacer(modifier = Modifier.height(60.dp))
            val enabled: Boolean
            if (mail.isNotEmpty() && pass.isNotEmpty()) {
                enabled = true
            } else {
                enabled = true
            }
            var text: String = ""
            val context = LocalContext.current
            val activity = LocalContext.current as Activity

            LogInButton(text = "Log Ind", onClick = {
                runBlocking {
                viewModel.SignIn("test123@gmail.com", "test123", context, activity) }
               if(FirebaseAuth.getInstance().currentUser!=null)
                    navMain() }
            , enabled)
            Text(text=text)
            Spacer(modifier = Modifier.height(40.dp))
            ChangePageText("Ny til Oplev? Opret Bruger Nu!", navigation)

        }

    }
}