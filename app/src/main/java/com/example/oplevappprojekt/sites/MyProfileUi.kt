package com.example.oplevappprojekt.sites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.Auth
import com.example.oplevappprojekt.ViewModel.AuthViewModel
import com.example.oplevappprojekt.data.MyUserProfileRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking


//s215726 & 216237

@Preview
@Composable
fun profilePrev(){
    UserProfile({}, {}, AuthViewModel(), {},{})
}
val repositoryUser = MyUserProfileRepository()
@Composable
fun UserProfile(navMain: () -> Unit, navigationInspo: () -> Unit, viewModel: AuthViewModel, navStart:()->Unit, navChange:()->Unit){
   val state = viewModel.uiState.value
    Scaffold(bottomBar = {BottomBar(onClick1 = {navigationInspo()}, onClick2 = {navMain()}, onClick3 = {})},
        content =
        {
        Logo()
        /*painter = painterResource(id = R.drawable.oplev_logo_lille),
        contentDescription = "logo",
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopStart,
        modifier = Modifier
            .absoluteOffset(10.dp,10.dp)
         */

    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Min Profil ",
            textAlign = TextAlign.Center,
            color = Color(myColourString.toColorInt()),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Image(painter = painterResource(id = R.drawable.user),
            contentDescription = "profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(190.dp)


            )
        Spacer(modifier = Modifier.height(5.dp))
        /*Text(
            text = "Skift profilbillede",
            textAlign = TextAlign.Center,
            color = Color(myColourString.toColorInt()),
            fontSize = 15.sp,

         */
        //Spacer(modifier = Modifier.height(40.dp))

         Box(modifier = Modifier
            .height(130.dp)
            .width(330.dp)



         ) {
            Text(
                text = "Navn:" + runBlocking { repositoryUser.readName() }, fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                fontSize = 20.sp,
                modifier = Modifier.absoluteOffset(10.dp, 11.dp)
            )


            Text(
                text = "E-mail: " + runBlocking { repositoryUser.readMail() }, fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp,
                modifier = Modifier
                    .absoluteOffset(10.dp, 5.dp)
                    .padding(top = 57.dp)
            )

           /* Text(
                text = "Mobil:",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .absoluteOffset(10.dp, 5.dp)
                    .padding(top = 110.dp))

            */


        }
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(onClick = {navChange()}) {
            Text("Skift kodeord",
                color = Color(myColourString.toColorInt()))
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { viewModel.logout()
            navStart()},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(myColourString.toColorInt())),
            modifier = Modifier
                .height(40.dp)
                .width(110.dp)

        )  {
            Text(text = "Log ud",
                color = Color.White)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { viewModel.deleteUser()
       navStart() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(myColourString.toColorInt())),
            modifier = Modifier
                .height(40.dp)
                .width(110.dp)
        ){
            Text(text = "Slet profil",
                color = Color.White)
        }
        Text(
            text = "Verifikations status: " + runBlocking { repositoryUser.userVerified() },
            color = Color(myColourString.toColorInt()),
            fontSize = 15.sp,
            modifier = Modifier.absoluteOffset(10.dp, 11.dp)
        )
    }
})
}



