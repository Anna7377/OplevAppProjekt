package com.example.oplevappprojekt.sites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.R

//s215726 &
@Preview
@Composable
fun profilePrev(){
    UserProfile()
}

@Composable
fun UserProfile(){
    Image(
        painter = painterResource(id = R.drawable.oplev_logo_lille),
        contentDescription = "logo",
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopStart,
        modifier = Modifier
            .absoluteOffset(10.dp,10.dp)

    )

    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Min Profil",
            textAlign = TextAlign.Center,
            color = Color(myColourString.toColorInt()),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Image(painter = painterResource(id = R.drawable.profile_pic),
            contentDescription = "profile",
            contentScale = ContentScale.Crop,


            )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Skift profilbillede",
            textAlign = TextAlign.Center,
            color = Color(myColourString.toColorInt()),
            fontSize = 15.sp,

            )
        Spacer(modifier = Modifier.height(40.dp))

        Box(modifier = Modifier
            .height(160.dp)
            .width(330.dp)
            .background(Color(myColourString.toColorInt()))


        ) {
            Text(
                text = "Navn:",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.absoluteOffset(10.dp, 11.dp)
            )



            Text(
                text = "E-mail:",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .absoluteOffset(10.dp, 5.dp)
                    .padding(top = 57.dp)
            )

            Text(
                text = "Mobil:",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .absoluteOffset(10.dp, 5.dp)
                    .padding(top = 110.dp))


        }
        Spacer(modifier = Modifier.height(90.dp))

        Button(onClick = {
        },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(myColourString.toColorInt())),
            modifier = Modifier
                .height(30.dp)
                .width(100.dp)

        )  {
            Text(text = "Log ud",
                color = Color.White)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
        },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(myColourString.toColorInt())),
            modifier = Modifier
                .height(30.dp)
                .width(100.dp)
        ){
            Text(text = "Slet profil",
                color = Color.White)
        }
    }
}


