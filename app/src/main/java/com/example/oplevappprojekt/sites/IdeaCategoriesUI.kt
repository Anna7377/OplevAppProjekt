package com.example.oplevappprojekt.sites

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.oplevappprojekt.R


class IdeaCategoriesUI{

}

//S215722

@Composable
fun Categories() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .height(20.dp)
                .width(20.dp)
                .absoluteOffset(15.dp, 15.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)){
                Logo()
                SingleJourneyTitle(text = "Min Rejse")
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top=80.dp)) {
            TopCard(text="Danmark", ImageId = R.drawable.image10)
            SingleJourneyTitle(text = "Kategorier")
            // Insert Lazy Colum here
        }
    }
}


@Preview
@Composable
fun CategoryPrev(){
    Categories()
}
