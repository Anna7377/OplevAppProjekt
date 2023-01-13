package com.example.oplevappprojekt.sites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.R

class SingleTrip {
}

@Composable
fun FinalTrip(){

    Image(
        painter = painterResource(id = R.drawable.oplev_logo_lille),
        contentDescription = "Logo",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .absoluteOffset(10.dp,10.dp)

    )
   Column(
       modifier = Modifier
           .fillMaxSize(),
       verticalArrangement = Arrangement.Top,
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
        Text(
            text = "Min Rejse",
            color = Color(myColor.toColorInt()),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,

        )
        Spacer(modifier = Modifier.height(15.dp))

        Image(
        painter = painterResource(id = R.drawable.danmark),
        contentDescription = "City",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(220.dp)
            .width(370.dp)
            .clip(RoundedCornerShape(50.dp))


    )
}
}

